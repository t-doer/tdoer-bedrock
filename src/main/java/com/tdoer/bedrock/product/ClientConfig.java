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
package com.tdoer.bedrock.product;

import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.service.Service;

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ClientConfig {

    /**
     * Get the application installation in the tenant's client of specific Id.
     *
     * @param applicationId
     * @return
     */
    ClientApplicationInstallation getApplicationInstallation(Long applicationId);

    /**
     * Check whether the tenant's client supports the application of specific Id,
     * that's, the application is installed in the tenant' client.
     *
     * @param application
     * @return
     */
    boolean supportApplication(Application application);

    /**
     * List application installation in the tenant's client
     *
     * @param list
     */
    void listApplicationInstallation(List<ClientApplicationInstallation> list);

    /**
     * Get the accessible service of specific Id
     *
     * @param serviceId
     * @return
     */
    ClientServiceInstallation getServiceInstallation(Long serviceId);

    /**
     * Check whether the tenant's client can access the service
     *
     * @param service
     * @return
     */
    boolean isServiceAccessible(Service service);

    /**
     * List accessible service for the tenant's client
     *
     * @param list
     */
    void listAccessibleService(List<ClientServiceInstallation> list);

    /**
     * The context installation in the tenant's client of specific context path
     *
     * @param contextPath
     * @return
     */
    ClientContextInstallation getContextInstallation(ContextPath contextPath);

    /**
     * Check whether the tenant's client supports the context instance, that's,
     * the context is installed in the tenant's client
     *
     * @param contextInstance
     * @return
     */
    boolean supportContext(ContextInstance contextInstance);

    /**
     * Lists the context installation in the tenant's client
     *
     * @param list
     */
    void listContextInstallation(List<ClientContextInstallation> list);


    /**
     * Get token configuration of the tenant's client
     *
     * @return
     */
    TokenConfig getTokenConfig();

}
