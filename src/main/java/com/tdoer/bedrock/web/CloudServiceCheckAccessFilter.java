/*
 * Copyright 2019 T-Doer (tdoer.com).
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

import com.tdoer.bedrock.CloudEnvironment;
import com.tdoer.bedrock.Platform;
import com.tdoer.bedrock.PlatformConstants;
import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.product.Client;
import com.tdoer.bedrock.service.Service;
import com.tdoer.bedrock.service.ServiceType;
import com.tdoer.bedrock.service.UnknownServiceTypeException;
import com.tdoer.springboot.http.StatusCodes;
import com.tdoer.springboot.util.WebUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 4 service types: GATEWAY, AUTHORIZATION, BUSINESS and INFRASTRUCTURE.
 * <ul>
 *  <li>Gateway service permits request from any client and any application,
 *      but forbids request from any service.</li>
 *  <li>Business service permits request from only specific clients, specific
 *      application and specific service.</li>
 *  <li>Authorization service permits request from only specific clients, specific
 *      applications, and any service.</li>
 *  <li>Infrastructure service permits request from only specific clients, specific
 *      application and any service.</li>
 * </ul>
 * <p>
 * Request from application can only access exposed service methods.
 *</p>
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-18
 */
public class CloudServiceCheckAccessFilter implements Filter, InitializingBean {
    private static Logger logger = LoggerFactory.getLogger(CloudServiceCheckAccessFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        CloudEnvironment env = Platform.getCurrentEnvironment();
        Service currentService = Platform.getCurrentService();

        // If referer service is null, it means that the request is directly
        // from an application in a client, say, browser or APP
        Service refererService = null;
        String refererServiceCode = WebUtil.findValueFromRequest(request, PlatformConstants.SERVICE_CODE);
        if(StringUtils.hasText(refererServiceCode)) {
            refererService = Platform.getServiceRepository().getService(refererServiceCode);
        }

        boolean permitted = false;
        switch (currentService.getType()){
            case GATEWAY:
                // permits request from any client and any application,
                // but forbids request from any service
                if(refererService != null){
                    logger.error("Gateway service ({}) forbids request from any service, so the request from " +
                                    "service ({}) is not permitted",
                            currentService.getCode(), refererService.getCode());
                    permitted = false;
                }else{
                    permitted = true;
                }
                break;
            case BUSINESS:
                // permits request from only specific clients, specific
                // application and specific service
                if(refererService == null || refererService.getType() == ServiceType.GATEWAY){
                    permitted = checkAccessFromClient(currentService, env.getClient()) &&
                            checkAccessFromApplication(currentService, env.getApplication());
                } else{
                    permitted = checkAccessFromRefererService(currentService, refererService);
                }
                break;
            case INFRASTRUCTURE:
            case AUTHORIZATION:
                // permits request from only specific clients, specific applications
                // but permits request from any service
                if(refererService == null || refererService.getType() == ServiceType.GATEWAY){
                    permitted = checkAccessFromClient(currentService, env.getClient()) &&
                            checkAccessFromApplication(currentService, env.getApplication());
                }else{
                    permitted = true;
                }
                break;
            default:
                logger.warn("Unknown service type: {}", currentService.getType());
                throw new UnknownServiceTypeException(currentService.getType());
        }

        // Request from application can only access exposed service methods.
        if(permitted
                && (refererService == null || refererService.getType() == ServiceType.GATEWAY)
                && currentService.getType() == ServiceType.GATEWAY){
            permitted = checkServiceMethod(currentService, request);
        }

        if(permitted){
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            logger.warn("Service ({}) dose not permit the quest: {}", currentService.getCode(),
                    request.getRequestURL());
            resp.setStatus(StatusCodes.FORBIDDEN);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    private boolean checkAccessFromClient(Service currentService, Client client){
        if(!currentService.permitAccessFromClient(client)){
            logger.warn("Service ({}) dose not permit the access request from the client ({})", currentService.getCode(),
                    client.getCode());
            return false;
        }
        return true;
    }

    private boolean checkAccessFromApplication(Service currentService, Application application){
        if(!currentService.permitAccessFromApplication(application)){
            logger.warn("Service ({}) dose not permit the access request from the application ({})",
                    currentService.getCode(), application.getCode());
            return false;
        }
        return true;
    }

    private boolean checkAccessFromRefererService(Service currentService, Service refererService){
        if(!currentService.permitAccessFromService(refererService)){
            logger.warn("Service ({}) dose not permit the access request from the service ({})",
                    currentService.getCode(), refererService.getCode());
            return false;
        }
        return true;
    }

    private boolean checkServiceMethod(Service currentService, HttpServletRequest req){
        if(!currentService.matchRequest(req.getMethod(), req.getRequestURI())){
            logger.warn("Service ({}) dose not have exposed method match the request: method ({}), URI ({})",
                    currentService.getCode(), req.getMethod(), req.getRequestURI());
            return false;
        }
        return true;
    }
}
