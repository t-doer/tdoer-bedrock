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

import com.tdoer.bedrock.service.Service;

import java.io.Serializable;
import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Application extends Serializable {

    /**
     * Application Id
     *
     * @return Application Id
     */
    Long getId();

    /**
     * Application code
     *
     * @return Application code, must not be blank
     */
    String getCode();

    /**
     * Application name
     *
     * @return Application name, must not be blank
     */
    String getName();

    /**
     * Application description
     *
     * @return Application description, maybe be {@code Null}
     */
    String getDescription();

    /**
     * Application provider's name, it can be an organization or a person
     *
     * @return Provide's name, it must not be blank
     */
    String getProvider();

    /**
     * Application version
     *
     * @return Application version, must not be blank
     */
    String getVersion();

    /**
     * Get page of specific Id available in the application
     *
     * @param pageId Page Id, it cannot be <code>null</code>
     * @return Page if found, otherwise {@code null}
     */
    Page getPage(Long pageId);

    /**
     * Get page of specific code available in the application
     *
     * @param pageCode
     * @return Page if found, otherwise {@code null}
     */
    Page getPage(String pageCode);

    /**
     * List available pages of the application in current cloud environment
     * {@link com.tdoer.bedrock.CloudEnvironment}.
     *
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listCurrentPages(List<Page> list);

    /**
     * List the application's common pages, excluding customized ones.
     *
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listCommonPages(List<Page> list);

    /**
     * List the application's all enabled pages, including common and customized ones.
     *
     * @param list List to hold pages, cannot be <code>null</code>
     */
    void listAllPages(List<Page> list);

    /**
     * List the Ids of services the application needs to call in current cloud environment
     * {@link com.tdoer.bedrock.CloudEnvironment}.
     *
     * @param list List to hold service Ids, cannot be <code>null</code>.
     */
    void listCurrentRefereeServiceIds(List<Long> list);

    /**
     * List the Ids of services the application commonly needs to call.
     *
     * @param list List to hold service Ids, cannot be <code>null</code>.
     */
    void listCommonRefereeServiceIds(List<Long> list);

    /**
     * List the Ids of all services the application totally needs to call,
     * including common ones and cutomized ones.
     *
     * @param list List to hold service Ids, cannot be <code>null</code>.
     */
    void listAllRefereeServiceIds(List<Long> list);
    /**
     * Check whether the application can access the service or not. If the service
     * is installed for the tenant's client, it's accessible.
     *
     * @param service The service to check
     * @return true if the service is accessible, otherwise false
     */
    boolean isServiceAccessible(Service service);

}
