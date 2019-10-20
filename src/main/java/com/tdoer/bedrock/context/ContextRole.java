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

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextRole extends GrantedAuthority, Serializable {
    /**
     * Role Id
     * @return Role Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Role name, should be unique in a context type or instance
     * @return Role name, must not be blank
     */
    String getName();

    /**
     * Role code, should be unique in a context type or instance.
     * Role code is mainly used in program for access checking
     * @return Role code, must not be blank
     */
    String getCode();

    /**
     * The path of context (type or instance) in which the role is defined
     * @return Context path, must not be <code>null</code>
     */
    ContextPath getContextPath();

    /**
     * The Id of tenant in which the role is defined. If the role is a system
     * role, tenant Id will be zero.
     *
     * @return Tenant Id, must not be <code>null</code>
     */
    Long getTenantId();

    /**
     * List front-end resources which are authorized to the context role,
     * such like page, action and navigation etc.
     * @param list List to hold role authorities, cannot be <code>null</code>
     */
    void listFrontendResource(List<RoleAuthority> list);

    /**
     * List back-end resources, mainly service methods which are authorized to
     * the context role.
     *
     * @param list
     */
    void listServiceMethods(List<RoleAuthority> list);

    /**
     * Check if the role permits the request
     *
     * @param httpMethod HTTP method, must not be blank
     * @param URI Request URI, must not be blank
     * @return true if the request is permitted
     */
    boolean permitServiceMethodAccess(String httpMethod, String URI);

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    int hashCode();
}
