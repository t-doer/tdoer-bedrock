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
     * Get the service according to its id.
     *
     * @param serviceId
     * @return
     * @throws ServiceNotFoundException if the service is not found or disabled.
     */
    Service getService(Long serviceId) throws ServiceNotFoundException;

    /**
     * Get the service according to its code.
     *
     * @param serviceCode
     * @return
     * @throws ServiceNotFoundException if the service is not found or disabled.
     */
    Service getService(String serviceCode) throws ServiceNotFoundException;

    /**
     * List services which are loaded into the respository.
     *
     * @param list
     */
    void listServices(List<Service> list);

    /**
     * Get the service method according to its id.
     *
     * @param methodId
     * @return
     * @throws ServiceMethodNotFoundException if the service is not found or disabled.
     */
    ServiceMethod getServiceMethod(Long methodId) throws ServiceMethodNotFoundException;

    /**
     * List all available service methods of specific service Id and cloud environment elements.
     *
     * @param serviceId Service Id, cannot be null
     * @param applicationId Application Id, cannot be null
     * @param productId Product Id, cannot be null
     * @param clientId Client Id, cannot be null
     * @param tenantId Tenant Id, cannot be null
     * @param contextPath Context path, cannot be null
     * @param list List to hold service methods
     */
    void listServiceMethods(Long serviceId, Long applicationId, Long productId, Long clientId, Long tenantId,
                            ContextPath contextPath, List<ServiceMethod> list);

}
