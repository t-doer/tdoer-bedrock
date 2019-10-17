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

import com.tdoer.bedrock.Platform;
import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.product.Client;
import org.springframework.beans.factory.InitializingBean;

import javax.servlet.*;
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
        Long serviceId = Platform.getCurrentService().getId();
        Client client = Platform.getCurrentEnvironment().getClient();
        if(!client.getClientConfig().isServiceAccessible(serviceId)){
            // todo, access denied
        }

        Application application = Platform.getCurrentEnvironment().getApplication();
        if(!application.isServiceAccessible(serviceId)){
            // todo, access denied
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}
