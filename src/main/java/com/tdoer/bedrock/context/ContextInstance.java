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
 * @Description A context instance is a context in which applications are installed
 * and user is authorized with roles to play the applications. Bedrock inherent context
 * instance is tenant and user, other instances can be organizations, say, club, class,
 * committee, office etc.
 *
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
     * The Id of tenant to which the context instance belongs
     * @return Tenant Id, must not be <code>null</code>
     */
    Long getTenantId();

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
