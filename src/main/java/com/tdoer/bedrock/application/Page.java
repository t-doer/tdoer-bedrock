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

import com.tdoer.bedrock.resource.ResourceType;

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Page extends ApplicationResource {
    @Override
    default ResourceType getType() {
        return ResourceType.PAGE;
    }

    /**
     * Page name
     *
     * @return Page name, it must not be blank
     */
    String getName();

    /**
     * Page code
     *
     * @return Page code, it must not be blank
     */
    String getCode();

    /**
     * Page's URI
     *
     * @return Page's URI, it must not be blank
     */
    String getURI();

    /**
     * Get action of specific Id available in the application. If the action
     * exists, but it dose not below to the application, it will return <code>null</code>
     *
     * @param actionId Action Id, cannot be <code>null</code>
     * @return Action if the action exists and is enabled and below to the application,
     * otherwise return <code>null</code>
     */
    Action getAction(Long actionId);

    /**
     * Get action of specific code available in the application. If the action
     * exists, but it dose not below to the application, it will return <code>null</code>
     *
     * @param actionCode Action code, cannot be <code>null</code>
     * @return Action if the action exists and is enabled and below to the application,
     * otherwise return <code>null</code>
     */
    Action getAction(String actionCode);

    /**
     * List actions of the page available in current environment {@link com.tdoer.bedrock.CloudEnvironment},
     *
     * @param list List to hold actions, cannot be <code>null</code>
     */
    void listCurrentActions(List<Action> list);

    /**
     * List the page's common actions, excluding customized ones.
     *
     * @param list List to hold actions, cannot be <code>null</code>
     */
    void listCommonActions(List<Action> list);

    /**
     * List the page's all enabled actions, including common and customized ones.
     *
     * @param list List to hold actions, cannot be <code>null</code>
     */
    void listAllActions(List<Action> list);
}
