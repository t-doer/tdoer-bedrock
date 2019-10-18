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

import com.tdoer.bedrock.CloudConstants;
import com.tdoer.bedrock.Platform;
import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.product.Client;
import com.tdoer.bedrock.service.Service;
import com.tdoer.bedrock.service.ServiceType;
import com.tdoer.springboot.util.WebUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-18
 */
public class ServiceCheckAccessFilter  implements Filter, InitializingBean {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;

        Service service = Platform.getCurrentService();
        Client client = Platform.getCurrentEnvironment().getClient();
        if(!service.permitAccessFromClient(client)){
            // todo, access denied
        }

        Application application = Platform.getCurrentEnvironment().getApplication();
        if(!service.permitAccessFromApplication(application)){
            // todo, access denied
        }

        if(service.getType() != ServiceType.GATEWAY){
            String refererServiceCode = WebUtil.findValueFromRequest(request, CloudConstants.SERVICE_CODE);
            if(StringUtils.hasText(refererServiceCode)){
                Service referer = Platform.getServiceRepository().getService(refererServiceCode);
                if(referer == null){
                    // todo, unknown service
                }else{
                    if(!service.permitAccessFromService(referer)){
                        // todo, access denied
                    }
                }
            }else{
                // todo, referer service code not found in request
            }
        }

        if(!service.matchRequest(request.getMethod(), request.getRequestURI())){
            // todo, no service method matches the request
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
