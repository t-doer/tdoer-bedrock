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
package com.tdoer.bedrock.resource;

import com.tdoer.bedrock.context.ContextPath;

import java.io.Serializable;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Resource extends Serializable {

    /**
     * Resource Id
     *
     * @return Resource Id
     */
    Long getId();

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
     * Resource category
     *
     * @return Resource category
     */
    ResourceCategory getCategory();

    /**
     * Resource type
     *
     * @return Resource type
     */
    ResourceType getType();

    /**
     * Tenant Id, to which the resource belongs to
     *
     * @return Tenant Id, it may be {@code Null}
     */
    Long getTenantId();

    /**
     * Client Id, to which the resource belongs to
     *
     * @return Client Id, it may be {@code Null}
     */
    String getClientId();

    /**
     * Context path, to which the resource belongs to
     *
     * @return Context path, it may be {@code Null}
     */
    ContextPath getContextPath();
}
