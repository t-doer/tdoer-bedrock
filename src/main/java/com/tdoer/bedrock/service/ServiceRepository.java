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
package com.tdoer.bedrock.service;

import com.tdoer.bedrock.context.ContextPath;

import java.util.List;

/**
 * @Description Service repository holds all enabled services and service methods.
 *
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ServiceRepository {

    /**
     * Get the service of specific id.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @return {@link Service}
     * @throws ServiceNotFoundException if the service dose not exist or is disabled.
     */
    Service getService(Long serviceId) throws ServiceNotFoundException;

    /**
     * Get the service according to its code.
     *
     * @param serviceCode Service code, cannot be <code>null</code>
     * @return {@link Service}
     * @throws ServiceNotFoundException if the service dose not exist or is disabled.
     */
    Service getService(String serviceCode) throws ServiceNotFoundException;

    /**
     * List a service's all referer client Ids.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param list List to hold client Ids, cannot be <code>null</code>
     */
    void listRefererClientIds(Long serviceId, List<Long> list);

    /**
     * List a service's all referer application Ids
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param list List to hold application Ids, cannot be <code>null</code>
     */
    void listRefererApplicationIds(Long serviceId, List<Long> list);

    /**
     * List a service's all referer services.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param list List to hold service Ids, cannot be <code>null</code>
     */
    void listRefererServiceIds(Long serviceId, List<Long> list);

    /**
     * List a service's all referee service Ids.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param list List to hold service Ids, cannot be <code>null</code>
     */
    void listRefereeServiceIds(Long serviceId, List<Long> list);

    /**
     * Get the service method of specific id.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param methodId Method Id, cannot be <code>null</code>
     * @return {@link ServiceMethod}
     * @throws ServiceMethodNotFoundException if the service method dose not exist or is disabled.
     */
    ServiceMethod getServiceMethod(Long serviceId, Long methodId) throws ServiceMethodNotFoundException;

    /**
     * List all service methods of specific service Id which are available for current environment.
     *
     * @param serviceId Service Id, cannot be <code>null</code>
     * @param applicationId Application Id, cannot be <code>null</code>
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path, cannot be <code>null</code>
     * @param list List to hold service methods, cannot be <code>null</code>
     */
    void listCurrentServiceMethods(Long serviceId, Long applicationId, Long productId, Long clientId, Long tenantId,
                            ContextPath contextPath, List<ServiceMethod> list);

    /**
     * List a service's all available service methods, including common and customized ones
     * @param list List to hold service methods, cannot be <code>null</code>
     */
    void listAllServiceMethods(Long serviceId, List<ServiceMethod> list);
}
