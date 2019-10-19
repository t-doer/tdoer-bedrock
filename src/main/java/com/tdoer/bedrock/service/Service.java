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

import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.product.Client;

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Service extends Serializable {
    /**
     * Service Id
     * @return Service Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Service code
     *
     * @return Service code, must not be <code>null</code>
     */
    String getCode();

    /**
     * Service type
     *
     * @return Service type, must not be <code>null</code>
     */
    ServiceType getType();

    /**
     * Service provider's name
     *
     * @return Service provider, must not be <code>null</code>
     */
    String getProvider();

    /**
     * Service name
     *
     * @return Service name, must not be <code>null</code>
     */
    String getName();

    /**
     * Service description
     *
     * @return Service description, maybe be {@code Null}
     */
    String getDescription();

    /**
     * Service version
     *
     * @return Service version, must not be <code>null</code>
     */
    String getVersion();

    /**
     * List the service's service methods which are available in current cloud environment
     * {@link com.tdoer.bedrock.CloudEnvironment}.
     *
     * @param list List to hold service methods, cannot be <code>null</code>.
     */
    void listCurrentMethods(List<ServiceMethod> list);

    /**
     * List the service's all enabled service methods, including common and customized
     * ones.
     *
     * @param list List to hold service methods, cannot be <code>null</code>.
     */
    void listAllMethods(List<ServiceMethod> list);

    /**
     * Get available service method of specific method Id in the service
     *
     * @param methodId Method Id
     * @return Service method if found
     * @throws ServiceMethodNotFoundException
     */
    ServiceMethod getMethod(Long methodId) throws ServiceMethodNotFoundException;

    /**
     * List the clients which refer to or call the service
     *
     * @param list List to hold clients, cannot be <code>null</code>
     */
    void listRefererClients(List<Client> list);

    /**
     * List applications which refer to or call the service
     * @param list
     */
    void listRefererApplications(List<Application> list);

    /**
     * List other services which refer to or call the service
     * @param list
     */
    void listRefererServices(List<Service> list);

    /**
     * List other services to which the service refers
     * @param list
     */
    void listRefereeServices(List<Service> list);

    /**
     * Is the service permits the access from the referer service?
     *
     * @param service
     * @return
     */
    boolean permitAccessFromService(Service service);

    /**
     * Is the service permits the access from the referer application?
     *
     * @param application
     * @return
     */
    boolean permitAccessFromApplication(Application application);

    /**
     * Is the service permits the access from the referer client?
     *
     * @param client
     * @return
     */
    boolean permitAccessFromClient(Client client);

    /**
     * Is the service match the request going on?
     * @param httpMethod
     * @param requestURI
     * @return
     */
    boolean matchRequest(String httpMethod, String requestURI);
}
