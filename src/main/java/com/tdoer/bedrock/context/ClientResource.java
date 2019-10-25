/*
 * Copyright 2019 T-Doer (tdoer.com).
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

import com.tdoer.bedrock.resource.Resource;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-25
 */
public interface ClientResource {
    /**
     * The Id of the tenant to which the resource belongs to
     * @return Tenant Id, cannot be <code>null</code>
     */
    Long getTenantId();

    /**
     * The context path of context in which the resource is defined
     * @return Context path cannot be null
     */
    ContextPath getContextPath();

    /**
     * The Id of the client of which the resource belongs to
     * @return Client Id, cannot be <code>null</code>
     */
    Long getClientId();

    /**
     * The resource defined or installed in the context
     * @return Resource, cannot be <code>null</code>
     */
    Resource getResource();
}
