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
    String getId();

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
     * Application author's name
     *
     * @return Application author's name, may be {@code Null}
     */
    String getAuthor();

    /**
     * Application maintainers, user names delimited by comma
     *
     * @return Application maintainers, may be {@code Null}
     */
    String getMaintainers();

    /**
     * Application version
     *
     * @return Application version, maybe be {@code Null}
     */
    String getVersion();

    /**
     * List available pages in current environment {@link com.tdoer.bedrock.CloudEnvironment},
     * that's, list available page according to current client, tenant and context instance.
     * <br>
     * Pages will be appended to the given list.
     *
     * @param list List to hold pages.
     */
    void listCurrentPages(List<Page> list);

    /**
     * List available services in current environment {@link com.tdoer.bedrock.CloudEnvironment},
     * that's, list available services according to current client, tenant and context instance.
     * <br>
     * Services will be appended to the given list.
     *
     * @param list List to hold services.
     */
    void listCurrentServices(List<Service> list);

    /**
     * Get page of specific Id available in the application
     *
     * @param pageId
     * @return Page if found, otherwise {@code null}
     */
    Page getPage(Long pageId);

    /**
     * Get action of specific Id available in the application
     *
     * @param actionId
     * @return
     */
    Action getAction(Long actionId);

}
