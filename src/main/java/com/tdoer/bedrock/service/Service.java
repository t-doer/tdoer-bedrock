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
     * List the service's methods which are available in current cloud environment
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
     */
    ServiceMethod getMethod(Long methodId);

    /**
     * List the clients which refer to or call the service
     *
     * @param list List to hold clients, cannot be <code>null</code>
     */
    void listRefererClients(List<Client> list);

    /**
     * List applications which refer to or call the service
     * @param list List to hold applications, cannot be <code>null</code>
     */
    void listRefererApplications(List<Application> list);

    /**
     * List other services which refer to or call the service
     * @param list List to hold service, cannot be <code>null</code>
     */
    void listRefererServices(List<Service> list);

    /**
     * List other services to which the service refers
     * @param list List to hold service, cannot be <code>null</code>
     */
    void listRefereeServices(List<Service> list);

    /**
     * Check if the service permits the access from the referer service?
     *
     * @param referer The referer service to check, cannot be <code>null</code>
     * @return true if the service permits the access
     */
    boolean permitAccessFromService(Service referer);

    /**
     * Check if the service permits the access from the referer application?
     *
     * @param application The referer application to check, cannot be <code>null</code>
     * @return true if the service permits the access
     */
    boolean permitAccessFromApplication(Application application);

    /**
     * Check if the service permits the access from the referer client?
     *
     * @param client The client to check, cannot be <code>null</code>
     * @return true if the service permits the access
     */
    boolean permitAccessFromClient(Client client);

    /**
     * Check if the service has a method which matchs the HTTP request
     *
     * @param httpMethod Http method
     * @param requestURI Request URI
     * @return true if there is one matched service method
     */
    boolean matchRequest(String httpMethod, String requestURI);
}
