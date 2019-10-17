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
     * @return Application code
     */
    String getCode();

    /**
     * Application name
     *
     * @return Application name
     */
    String getName();

    /**
     * Application description
     *
     * @return Application description, maybe be {@code Null}
     */
    String getDescription();

    /**
     * 应用提供商，可以是个人或组织。
     *
     * @return 应用提供商，可能为{@code null}
     */
    String getProvider();

    /**
     * Application version
     *
     * @return Application version, maybe be {@code Null}
     */
    String getVersion();

    /**
     * List available pages of the application according to current request environment
     * {@link com.tdoer.bedrock.CloudEnvironment}.
     * <br>
     * Pages will be appended to the given list.
     *
     * @param list List to hold pages.
     */
    void listCurrentPages(List<Page> list);

    /**
     * List services the application needs to call in current environment {@link com.tdoer.bedrock.CloudEnvironment}.
     * <br>
     * Services will be appended to the given list.
     *
     * @param list List to hold services.
     */
    void listCurrentServices(List<Service> list);

    /**
     * Whether the application can access the service or not
     *
     * @param serviceId
     * @return
     */
    boolean isServiceAccessible(Long serviceId);

    /**
     * Get page of specific Id available in the application
     *
     * @param pageId
     * @return Page if found, otherwise {@code null}
     */
    Page getPage(Long pageId) throws PageNotFoundException;

    /**
     * Get page of specific code available in the application
     *
     * @param pageCode
     * @return Page if found, otherwise {@code null}
     */
    Page getPage(String pageCode) throws PageNotFoundException;

    /**
     * Get action of specific Id available in the application
     *
     * @param actionId
     * @return
     */
    Action getAction(Long actionId) throws ActionNotFoundException;

    /**
     * Get action of specific code available in the application
     *
     * @param actionCode
     * @return
     */
    Action getAction(String actionCode) throws ActionNotFoundException;
}
