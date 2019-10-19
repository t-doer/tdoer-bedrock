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
package com.tdoer.bedrock.application;

import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.service.Service;
import com.tdoer.bedrock.service.ServiceMethod;

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ApplicationRepository {
    /**
     * Get application of specific Id
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @return Application if found
     * @throws ApplicationNotFoundException if application does not exist or is disabled
     */
    Application getApplication(Long applicationId) throws ApplicationNotFoundException;

    /**
     * Get application of specific code
     *
     * @param applicationCode Application code, cannot be <code>null</code>
     * @return Application if found
     * @throws ApplicationNotFoundException if application does not exist or is disabled
     */
    Application getApplication(String applicationCode) throws ApplicationNotFoundException;

    /**
     * List all available applications in the repository.
     *
     * @param list List to hold applications, cannot be <code>null</code>
     */
    void listApplications(List<Application> list);

    /**
     * Get page of specific Id
     *
     * @param pageId Page Id, cannot be null
     * @return Page if found
     * @throws PageNotFoundException if the page dose not exist or is disabled
     */
    Page getPage(Long pageId) throws PageNotFoundException;

    /**
     * Get page of specific page code in the application
     * @param applicationId application Id, cannot be <code>null</code>
     * @param pageCode page code, cannot be <code>null</code>
     * @return Page if found
     * @throws PageNotFoundException if the page dose not exist or is disabled
     */
    Page getPage(Long applicationId, String pageCode) throws PageNotFoundException;

    /**
     * List pages of specific application which are available for current product, client, tenant, and context instance.
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path, cannot be <code>null</code>
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listCurrentPages(Long applicationId, Long productId, Long clientId, Long tenantId, ContextPath contextPath,
                   List<Page> list);

    /**
     * List an application's all available pages, including common and customized ones.
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listAllPages(Long applicationId, List<Page> list);

    /**
     * Get action of specific Id
     *
     * @param actionId Action Id, cannot be <code>null</code>
     * @return Action if found
     * @throws ActionNotFoundException if the action dose not exist or is disabled
     */
    Action getAction(Long actionId) throws ActionNotFoundException;

    /**
     * Get action of specific action code in the page
     * @param pageId page Id, cannot be <code>null</code>
     * @param actionCode action code, cannot be <code>null</code>
     * @return Action if found
     * @throws ActionNotFoundException if the action dose not exist or is disabled
     */
    Action getAction(Long pageId, String actionCode) throws ActionNotFoundException;

    /**
     * List actions of specific page of the application which are available for
     * current product, client, tenant, and context instance.
     *
     * @param pageId Page Id, cannot be <code>null</code>
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path, cannot be <code>null</code>
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listCurrentActions(Long pageId, Long applicationId, Long productId, Long clientId, Long tenantId,
                     ContextPath contextPath, List<Action> list);

    /**
     * List all available actions of a page, including common and customized ones
     *
     * @param pageId Page Id, cannot be <code>null</code>
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listAllActions(Long pageId, List<Action> list);

    /**
     * List the referee services which are referred to or called by specific application,
     * and are available for current product, client, tenant, and context instance.
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path, cannot be <code>null</code>
     * @param list List to referee services, cannot be <code>null</code>
     */
    void listCurrentRefereeServices(Long applicationId, Long productId, Long clientId, Long tenantId,
                               ContextPath contextPath,
                      List<Service> list);

    /**
     * List all available referee services of the application.
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param list List to referee services, cannot be <code>null</code>
     */
    void listAllRefereeServices(Long applicationId, List<Service> list);

    /**
     * List the service methods which the action needs to call
     * @param actionId Action Id, cannot be <code>null</code>
     * @param list List to hold service methods, cannot be <code>null</code>
     */
    void listServiceMethodsOfAction(Long actionId, List<ServiceMethod> list);

    /**
     * List the service methods which will be called when the page is being loaded
     *
     * @param pageId Page Id, cannot be <code>null</code>
     * @param list List to hold service methods, cannot be <code>null</code>
     */
    void listServiceMethodsOfPage(Long pageId, List<ServiceMethod> list);
}
