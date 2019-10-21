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
import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.tenant.ProductRental;
import com.tdoer.bedrock.tenant.TenantClient;
import com.tdoer.springboot.error.ErrorCodeException;
import com.tdoer.springboot.util.LocaleUtil;
import com.tdoer.springboot.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class RequestCloudEnvironmentExtractor {

    private final static Logger logger = LoggerFactory.getLogger(RequestCloudEnvironmentExtractor.class);

    protected EnvironmentDigest extractEnvironmentDigest(HttpServletRequest request) {

        String digestString = WebUtil.findValueFromRequest(request, CloudConstants.ENVIRONMENT_DIGEST);
        if (digestString != null) {
            return EnvironmentDigest.fromDigestString(digestString);
        }

        return null;
    }

    protected TenantClient extractTenantClient(HttpServletRequest request) {

        TenantClient tenantClient = null;

        String tenantId = WebUtil.findValueFromRequest(request, CloudConstants.TENANT_GUID);
        String clientId = WebUtil.findValueFromRequest(request, CloudConstants.CLIENT_CODE);
        if (StringUtils.hasText(clientId) && StringUtils.hasText(tenantId)) {
            logger.debug("Loading TenantClient by (tenantId, clientId) - ({}, {})", tenantId, clientId);
            tenantClient = Platform.getRentalCenter().getTenantClient(Long.parseLong(tenantId), Long.parseLong(clientId));
            if (tenantClient == null) {
                throw new InvalidRequestException(BedrockErrorCodes.NO_TENANT_CLIENT_BY_IDS, tenantId, clientId);
            }
        } else {
            String host = request.getServerName();
            logger.debug("Loading TenantClient by request's provider name: {}", host);
            tenantClient = Platform.getRentalCenter().getTenantClient(host);
            if (tenantClient == null) {
                throw new InvalidRequestException(BedrockErrorCodes.NO_TENANT_CLIENT_BY_HOST, host);
            }
        }

        logger.debug("Loaded TenantClient: {}", tenantClient);

        return tenantClient;
    }

    protected ContextPath extractContextPath(HttpServletRequest request) {
        String cp = WebUtil.findValueFromRequest(request, CloudConstants.CONTEXT_PATH_PARAM);
        logger.debug("Found context path: {}", cp);
        if (StringUtils.hasText(cp)) {
            return Platform.getContextPathParser().parse(cp);
        }
        return null;
    }

    public CloudEnvironment extract(HttpServletRequest request) {
        CloudEnvironment ret = null;
        // Request from internal provider after zuul
        EnvironmentDigest digest = extractEnvironmentDigest(request);
        if (digest != null) {
            ret = buildFromDigest(digest);
        } else {
            // Request from public web (browser or app etc.)
            ret = buildFromRequest(request);
        }
        return ret;
    }

    protected CloudEnvironment buildFromRequest(HttpServletRequest request) {
        try {
            TenantClient tenantClient = extractTenantClient(request);
            ProductRental productRental = Platform.getRentalCenter().getProductRendtal(
                    tenantClient.getTenantId(), tenantClient.getClient().getProductId());
            if (productRental == null) {
                throw new InvalidRequestException(BedrockErrorCodes.PRODUCT_RENTAL_NOT_FOUND, tenantClient.getTenantId(),
                        tenantClient.getClient().getProductId());
            }

            ContextInstance contextInstance = null;
            ContextPath contextPath = extractContextPath(request);
            if (contextPath == null) {
                contextInstance = tenantClient.getTenant();
                logger.debug("No context path extracted from request, use tenant as default context instance: {}", contextInstance);
            } else {
                contextInstance = Platform.getContextInstanceCenter().getContextInstance(tenantClient.getTenantId(),
                        contextPath);
                if (contextInstance == null) {
                    throw new InvalidRequestException(BedrockErrorCodes.NO_CONTEXT_INSTANCE, contextPath);
                }
                if (!contextInstance.getContextPath().equals(contextPath)) {
                    throw new InvalidRequestException(BedrockErrorCodes.INVALID_CONTEXT_PATH, contextPath);
                }
            }

            String appCode = WebUtil.findValueFromRequest(request, CloudConstants.APPLICATION_CODE_PARAM);
            if(!StringUtils.hasText(appCode)){
                logger.debug("No application code found in request: {}", request.getRequestURL());
                appCode = contextInstance.getContextConfig().getEntryApplicationCode();
                logger.debug("Use context config's default application code: {}", appCode);
            }

            Application application = Platform.getApplicationRepository().getApplication(appCode);
            if (application == null) {
                throw new InvalidRequestException(BedrockErrorCodes.UNKNOWN_APPLICATION_ID, appCode);
            }

            Locale language = productRental.getDefaultLanguage();
            if (language == null) {
                language = Locale.SIMPLIFIED_CHINESE;
            }

            return new CloudEnvironment(productRental, tenantClient, contextInstance, application, language);

        } catch (InvalidRequestException req) {
            throw req;
        } catch (Throwable t) {
            throw new ErrorCodeException(BedrockErrorCodes.INTERNAL_SERVER_ERROR, t);
        }
    }

    protected CloudEnvironment buildFromDigest(EnvironmentDigest digest) {
        try {
            TenantClient tenantClient = Platform.getRentalCenter().getTenantClient(digest.getTenantId(), digest.getClientId());
            ProductRental productRental = Platform.getRentalCenter().getProductRendtal(digest.getTenantId(),
                    tenantClient.getClient().getProductId());
            ContextPath contextPath = Platform.getContextPathParser().parse(digest.getContextPath());
            ContextInstance contextInstance =
                    Platform.getContextInstanceCenter().getContextInstance(digest.getTenantId(), contextPath);
            Application application = Platform.getApplicationRepository().getApplication(digest.getApplicationId());
            Locale language = LocaleUtil.getLocale(digest.getLanguage());
            return new CloudEnvironment(productRental, tenantClient, contextInstance, application, language);
        } catch (Throwable t) {
            throw new InvalidRequestException(BedrockErrorCodes.INVALID_ENV_DIGEST, t, digest.toDigestString());
        }
    }

}
