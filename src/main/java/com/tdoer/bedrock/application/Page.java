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
     * Get the name, or a brief description, of the resource
     *
     * @return Resource name
     */
    String getName();

    /**
     * Resource code, for example, page's code, action's code, navigation item's node ID etc.
     *
     * @return Resource code
     */
    String getCode();

    /**
     * 页面的访问URI，肯定不为空
     *
     * @return 页面的访问URI，肯定不为空
     */
    String getURI();

    /**
     * List actions of the page available in current environment {@link com.tdoer.bedrock.CloudEnvironment},
     * that's, list available actions of the page available to current client, product, tenant and context instance.
     * <br>
     *
     * @param list
     */
    void listCurrentActions(List<Action> list);


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
