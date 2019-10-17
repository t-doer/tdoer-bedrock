/*
 * Copyright 2017-2019 T-Doer (tdoer.com).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tdoer.bedrock.web;

import com.tdoer.bedrock.*;
import com.tdoer.bedrock.product.ClientConfig;
import com.tdoer.bedrock.tenant.ProductRental;
import com.tdoer.springboot.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class CloudEnvironmentParseFilter implements Filter, InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(CloudEnvironmentParseFilter.class);


    protected RequestCloudEnvironmentExtractor extractor;

    public CloudEnvironmentParseFilter(RequestCloudEnvironmentExtractor extractor) {
        Assert.notNull(extractor, "RequestCloudEnvironmentExtractor cannot be null");
        this.extractor = extractor;
    }

    public void afterPropertiesSet() {

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
            ServletException {

        final HttpServletRequest request = (HttpServletRequest) req;
        final HttpServletResponse response = (HttpServletResponse) res;

        CloudEnvironmentHolder.clearEnvironment();
        logger.debug("Cleaned environment before to process request: {}", request.getRequestURL());

        CloudEnvironment environment = null;
        try {
            logger.debug("Parsing cloud environment for the request: {}", request.getRequestURL());
            environment = parseEnvironment(request, response);
            logger.info("Parsed out cloud environment {} for the request: {}", environment, request.getRequestURL());

            // Set to environment holder for later use
            CloudEnvironmentHolder.setEnvironment(environment);
            logger.debug("Parsed and attached new environment ({}) for the request: {}", environment, request.getRequestURL());

            logger.debug("Verifying cloud environment {} for the request: {}", environment, request.getRequestURL());
            verifyEnvironment(environment);
            logger.debug("Verified cloud environment {} for the request: {}", environment, request.getRequestURL());


            setResponseHeader(request, response, environment);

            chain.doFilter(request, response);

        } catch (InvalidRequestException ire) {
            // todo, response error
            ire.printStackTrace();
        } catch (Throwable t) {
            // todo, response error
            t.printStackTrace();
        } finally {
            logger.debug("Processed request: {}", request.getRequestURL());
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("{} is initialized", this.getClass().getSimpleName());
    }

    public void destroy() {
        logger.info("{} is destroyed", this.getClass().getSimpleName());
    }

    protected CloudEnvironment parseEnvironment(HttpServletRequest request, HttpServletResponse response) {
        return extractor.extract(request);
    }

    protected void verifyEnvironment(CloudEnvironment env) {
        // Check if the product rental is expired
        ProductRental productRental = env.getProductRental();
        if (productRental.isActive()) {
            throw new InvalidRequestException(BedrockErrorCodes.EXPIRED_PRODUCT_RENTAL, productRental.getStartDate(), productRental.getEndDate());
        }

        // Check if the tenant product supports the context instance
        ClientConfig clientConfig = env.getClientConfig();
        if (!clientConfig.supportContext(env.getContextPath())) {
            throw new InvalidRequestException(BedrockErrorCodes.CONTEXT_INSTANCE_NOT_SUPPORTED_BY_TENANT_CLIENT, env.getContextPath(), env.getTenantId(), env.getClientId());
        }

        // Check if the tenant product supports the application
        if (!clientConfig.supportApplication(env.getApplicationId())) {
            throw new InvalidRequestException(BedrockErrorCodes.APPLICATION_NOT_SUPPORTED_BY_TENANT_CLIENT, env.getApplicationId(), env.getTenantId(), env.getClientId());
        }

        // Check if the context instance supports the application
        if (!env.getContextConfig().supportApplication(env.getApplicationId())) {
            throw new InvalidRequestException(BedrockErrorCodes.APPLICATION_NOT_SUPPORTED_BY_CONTEXT_INSTANCE, env.getApplicationId(), env.getContextPath());
        }
    }

    protected void setResponseHeader(HttpServletRequest request, HttpServletResponse response, CloudEnvironment environment) {
        String tenantCode = WebUtil.findValueFromRequest(request, CloudConstants.TENANT_ID);
        String clientId = WebUtil.findValueFromRequest(request, CloudConstants.CLIENT_ID);

        if (!StringUtils.hasText(tenantCode) || !StringUtils.hasText(clientId)) {
            WebUtil.addValueIntoResponseHeaderAndCookie(response, request, CloudConstants.CLIENT_ID, environment.getClient().getCode());
            WebUtil.addValueIntoResponseHeaderAndCookie(response, request, CloudConstants.TENANT_ID, environment.getTenant().getCode());
        }

        request.setAttribute(CloudConstants.ENVIRONMENT_DIGEST, environment.getDigest());
    }
}
