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

    Long getId();

    String getName();

    String getCode();

    ContextPath getContextPath();

    Long getTenantId();

    /**
     * List front-end resource, such like page, action and navigation etc.
     * @param list
     */
    void listFrontendResource(List<RoleAuthority> list);

    /**
     * List back-end resource, mainly service methods.
     * @param list
     */
    void listServiceMethods(List<RoleAuthority> list);

    boolean permitServiceMethodAccess(String httpMethod, String path);

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    int hashCode();
}
