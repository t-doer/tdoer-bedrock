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
     * Organization Id or User Id
     *
     * @return
     */
    Long getInstanceId();

    /**
     * Organization Name or User Name
     *
     * @return
     */
    String getInstanceName();

    /**
     * Organization Code or User Login Account
     *
     * @return
     */
    String getCode();

    /**
     * Get contextPath instance's detail information object's ID, say, tenant's Id, store's Id, userI's Id etc.
     *
     * @return
     */
    Long getDetailObjectId();

    /**
     * Parent contextPath instance. If the instance is a tenant, its parent contextPath instance
     * is null; if the instance is a user, its parent is a tenant; if the instance is a
     * store, its parent is city.
     * <p>
     * The top parent is always the organization root, the organization node of a tenant.
     *
     * @return
     */
    ContextInstance getParent();

    /**
     * The top parent is always the organization root, the organization node of a tenant.
     *
     * @return
     */
    ContextInstance getTopParent();

    /**
     * Context path to the contextPath instance, say, '1.1-20.2-30.3'.
     *
     * @return
     */
    ContextPath getContextPath();

    ContextType getContextType();

    /**
     * The instance's configurations, for example, available applications, contextPath roles etc.
     *
     * @return
     */
    ContextConfig getContextConfig();
}
