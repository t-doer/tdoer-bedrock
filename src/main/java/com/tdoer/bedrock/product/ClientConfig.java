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
     * Get the application installation of specific application Id in the tenant's client.
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @return {@link ClientApplicationInstallation} if found, otherwise return <code>null</code>
     */
    ClientApplicationInstallation getApplicationInstallation(Long applicationId);

    /**
     * Check whether the tenant's client supports the application of specific Id,
     * that's, the application is installed in the tenant's client.
     *
     * @param application Application to check, cannot be <code>null</code>
     * @return true if the application is supported by the tenant's client
     */
    boolean supportApplication(Application application);

    /**
     * List application installation in the tenant's client
     *
     * @param list List to hold {@link ClientApplicationInstallation}, cannot be <code>null</code>
     */
    void listApplicationInstallation(List<ClientApplicationInstallation> list);

    /**
     * Get the service installation of specific service Id
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @return {@link ClientServiceInstallation} if it's exists
     */
    ClientServiceInstallation getServiceInstallation(Long serviceId);

    /**
     * Check whether the tenant's client can access the service
     *
     * @param service The service to check, it cannot be <code>null</code>
     * @return true if the service is accessible for the client
     */
    boolean isServiceAccessible(Service service);

    /**
     * List accessible service for the tenant's client
     *
     * @param list List to hold {@link ClientServiceInstallation}, cannot be <code>null</code>
     */
    void listAccessibleService(List<ClientServiceInstallation> list);

    /**
     * The context instance or context type installation in the tenant's client of specific context path
     *
     * @param contextPath The context path of a context instance, cannot be <code>null</code>
     * @return {@link ClientContextInstallation} if it exists and is enabled
     */
    ClientContextInstallation getContextInstallation(ContextPath contextPath);

    /**
     * Check whether the tenant's client supports the context instance, that's,
     * the context instance or context type is installed in the tenant's client
     *
     * @param contextInstance The context instance to check, cannot be <code>null</code>
     * @return true if the client supports the context instance
     */
    boolean supportContext(ContextInstance contextInstance);

    /**
     * Lists the context installation in the tenant's client
     *
     * @param list List to hold {@link ClientContextInstallation}, cannot be <code>null</code>
     */
    void listContextInstallation(List<ClientContextInstallation> list);


    /**
     * Get token configuration of the tenant's client
     *
     * @return {@link TokenConfig}, it must not be <code>null</code>
     */
    TokenConfig getTokenConfig();

}
