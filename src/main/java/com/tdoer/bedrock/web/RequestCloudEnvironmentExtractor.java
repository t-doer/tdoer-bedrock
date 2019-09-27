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
import com.tdoer.bedrock.application.ApplicationRepository;
import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextInstanceCenter;
import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.context.ContextPathParser;
import com.tdoer.bedrock.product.ClientConfigCenter;
import com.tdoer.bedrock.product.ContextInstallation;
import com.tdoer.bedrock.tenant.ProductRental;
import com.tdoer.bedrock.tenant.RentalCenter;
import com.tdoer.bedrock.tenant.Tenant;
import com.tdoer.bedrock.tenant.TenantClient;
import com.tdoer.springboot.error.ErrorCodeException;
import com.tdoer.springboot.util.LocaleUtil;
import com.tdoer.springboot.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
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

    private ContextInstanceCenter contextInstanceCenter;

    private RentalCenter rentalCenter;

    private ApplicationRepository applicationRepository;

    private ContextPathParser contextPathParser;

    private ClientConfigCenter clientConfigCenter;

    public void setContextInstanceCenter(ContextInstanceCenter contextInstanceCenter) {
        Assert.notNull(contextInstanceCenter, "ContextInstanceCenter cannot be null");

        this.contextInstanceCenter = contextInstanceCenter;
    }


    public void setRentalCenter(RentalCenter tenantService) {
        Assert.notNull(tenantService, "TenantService cannot be null");

        this.rentalCenter = tenantService;
    }

    public void setContextPathParser(ContextPathParser contextPathParser) {
        Assert.notNull(contextPathParser, "ContextPathParser cannot be null");

        this.contextPathParser = contextPathParser;
    }

    public void setApplicationRepository(ApplicationRepository applicationRepository) {
        this.applicationRepository = applicationRepository;
    }

    public void setClientConfigCenter(ClientConfigCenter clientConfigCenter) {
        this.clientConfigCenter = clientConfigCenter;
    }

    protected EnvironmentDigest extractEnvironmentDigest(HttpServletRequest request) {

        String digestString = WebUtil.findValueFromRequest(request, CloudConstants.ENVIRONMENT_DIGEST);
        if (digestString != null) {
            return EnvironmentDigest.fromDigestString(digestString);
        }

        return null;
    }

    protected TenantClient extractTenantClient(HttpServletRequest request) {

        TenantClient tenantClient = null;

        String tenantId = WebUtil.findValueFromRequest(request, CloudConstants.TENANT_ID);
        String clientId = WebUtil.findValueFromRequest(request, CloudConstants.CLIENT_ID);
        if (StringUtils.hasText(clientId) && StringUtils.hasText(tenantId)) {
            logger.debug("Loading TenantClient by (tenantId, clientId) - ({}, {})", tenantId, clientId);
            tenantClient = rentalCenter.getTenantClient(Long.parseLong(tenantId), clientId);
            if (tenantClient == null) {
                throw new InvalidRequestException(BedrockErrorCodes.NO_TENANT_CLIENT_BY_IDS, tenantId, clientId);
            }
        } else {
            String host = request.getServerName();
            logger.debug("Loading TenantClient by request's provider name: {}", host);
            tenantClient = rentalCenter.getTenantClient(host);
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
            return contextPathParser.parse(cp);
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
            ProductRental productRental = rentalCenter.getProductRendtal(tenantClient.getTenant().getId(), tenantClient.getClient().getProduct().getId());
            if (productRental == null) {
                throw new InvalidRequestException(BedrockErrorCodes.NO_PRODUCT_RENTAL, tenantClient.getTenant().getId(), tenantClient.getClient().getProduct().getId());
            }

            ContextInstance contextInstance = null;
            Tenant tenant = tenantClient.getTenant();
            ContextPath contextPath = extractContextPath(request);
            if (contextPath == null) {
                contextInstance = tenant;
                logger.debug("No context path extracted from request, use tenant as default context instance: {}", contextInstance);
            } else {
                contextInstance = contextInstanceCenter.getContextInstance(contextPath);
                if (contextInstance == null) {
                    throw new InvalidRequestException(BedrockErrorCodes.NO_CONTEXT_INSTANCE, contextPath);
                }
                if (!contextInstance.getContextPath().equals(contextPath)) {
                    throw new InvalidRequestException(BedrockErrorCodes.ILLEGAL_CONTEXT_PATH, contextPath);
                }
            }

            Application application = null;
            ContextInstallation ci = null;
            String appId = WebUtil.findValueFromRequest(request, CloudConstants.APPLICATION_ID_PARAM);
            if (!StringUtils.hasText(appId)) {
                logger.debug("No application Id found in request: {}", request.getRequestURL());
                ci = clientConfigCenter.getContextInstallation(contextPath, productRental.getProduct().getId(), tenantClient.getClient().getId(), tenant.getId());
                appId = ci.getEntryApplicationId();
                logger.debug("Use default application Id ({}) in client's context installation {}", appId, ci);
            }

            application = applicationRepository.getApplication(appId);
            if (application == null) {
                throw new InvalidRequestException(BedrockErrorCodes.UNKNOWN_APPLICATION_ID, appId);
            }

            Locale language = null;
            if (ci == null) {
                ci = clientConfigCenter.getContextInstallation(contextPath, productRental.getProduct().getId(), tenantClient.getClient().getId(), tenant.getId());
            }

            language = ci.getEntryLanguage();
            if (language == null) {
                language = Locale.SIMPLIFIED_CHINESE;
            }

            return new CloudEnvironment(tenant, productRental, tenantClient, contextInstance, application, language);

        } catch (InvalidRequestException req) {
            throw req;
        } catch (Throwable t) {
            throw new ErrorCodeException(BedrockErrorCodes.INTERNAL_SERVER_ERROR, t);
        }
    }

    protected CloudEnvironment buildFromDigest(EnvironmentDigest digest) {
        try {
            TenantClient tenantClient = rentalCenter.getTenantClient(digest.getTenantId(), digest.getClientId());
            ProductRental productRental = rentalCenter.getProductRendtal(digest.getTenantId(), tenantClient.getClient().getProduct().getId());

            Tenant tenant = rentalCenter.getTenant(digest.getTenantId());
            ContextInstance contextInstance = contextInstanceCenter.getContextInstance(contextPathParser.parse(digest.getContextPath()));
            Application application = applicationRepository.getApplication(digest.getApplicationId());
            Locale language = LocaleUtil.getLocale(digest.getLanguage());
            return new CloudEnvironment(tenant, productRental, tenantClient, contextInstance, application, language);
        } catch (Throwable t) {
            throw new InvalidRequestException(BedrockErrorCodes.INVALID_ENV_DIGEST, t, digest.toDigestString());
        }
    }

}
