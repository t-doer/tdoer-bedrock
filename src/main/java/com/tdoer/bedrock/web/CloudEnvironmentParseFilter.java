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
import com.tdoer.springboot.http.StatusCodes;
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
            logger.debug("Parsed out cloud environment {} for the request: {}", environment, request.getRequestURL());

            boolean valid = false;
            if(environment != null){
                logger.debug("Verifying cloud environment {} for the request: {}", environment, request.getRequestURL());
                valid = verifyEnvironment(environment);
                logger.debug("Verified cloud environment {} for the request: {}", environment, request.getRequestURL());
            }

            logger.info("Cloud environment {} for the request ({}) is valid: {}", environment,
                    request.getRequestURL(), valid);

            if(valid){
                // Set to environment holder for later use
                CloudEnvironmentHolder.setEnvironment(environment);
                // go on
                chain.doFilter(request, response);
            }else{
                logger.error("Request ({}) is denied because of invalid cloud environment {}",
                        request.getRequestURL(), environment);
                response.setStatus(StatusCodes.FORBIDDEN);
            }
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

    protected boolean verifyEnvironment(CloudEnvironment env) {
        // Check if the product rental is expired
        ProductRental productRental = env.getProductRental();
        if (!productRental.isActive()) {
            logger.warn("Product rental is not active: {}", productRental);
            return false;
        }

        // Check if the tenant product supports the context instance
        ClientConfig clientConfig = env.getClientConfig();
        if (!clientConfig.supportContext(env.getContextInstance())) {
            logger.warn("The tenant client ({}, {}) dose not support by context instance ({})",
                    env.getTenant().getCode(), env.getClient().getCode(), env.getContextPath());
            return false;
        }

        // Check if the tenant product supports the application
        if (!clientConfig.supportApplication(env.getApplication())) {
            logger.warn("The tenant client ({}, {}) dose not support by application ({})",
                    env.getTenant().getCode(), env.getClient().getCode(), env.getApplication().getCode());
            return false;
        }

        // Check if the context instance supports the application
        if (!env.getContextConfig().supportApplication(env.getApplication())) {
            logger.warn("The context instance ({}) dose not support application ({})",
                    env.getContextPath(), env.getApplication().getCode());
            return false;
        }

        return true;
    }

}
