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
package com.tdoer.bedrock.context;

import java.io.Serializable;

/**
 * @Description A contextPath instance is usually a organization (tenant/district/city/store/office etc.)
 * or a user.
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextInstance extends Serializable {
    /**
     * Context instance Id
     *
     * @return Instance Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Context instance guid, globally unique
     *
     * @return Instance GUID, must not be blank
     */
    String getGuid();

    /**
     * Context instance name, unique in a tenant
     *
     * @return Instance name, must not be blank
     */
    String getName();

    /**
     * Instance code, unique in a tenant
     *
     * @return Instance code, must not be blank
     */
    String getCode();

    /**
     * Get instance's detail information object's ID, say, class's Id, user's Id etc.
     *
     * @return associated detail object Id, may be <code>null</code>
     */
    Long getDetailObjectId();

    /**
     * Is the instance a tenant, the root context instance?
     *
     * @return true if the instance is a tenant
     */
    boolean isTenant();

    /**
     * Parent context instance. If the instance is a tenant, its parent context instance
     * is <code>null</code>
     *
     * @return The context instance's parent instance, may be <code>null</code>
     */
    ContextInstance getParent();

    /**
     * The top parent of the instance, that's the tenant.
     *
     * @return
     */
    ContextInstance getTopParent();

    /**
     * Context path to the context instance, say, '1.1-20.2-30.3', it's always
     * globally unique.
     *
     * @return Context path, must not be <code>null</code>
     */
    ContextPath getContextPath();

    /**
     * Context type of the context instance. An instance must below to only one
     * context type.
     *
     * @return Context type, must not be <code>null</code>
     */
    ContextType getContextType();

    /**
     * The instance's configurations, for example, available applications, context roles etc.
     *
     * @return Context configuration, must not be <code>null</code>
     */
    ContextConfig getContextConfig();
}
