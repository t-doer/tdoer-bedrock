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

import com.tdoer.bedrock.application.ApplicationInstallation;
import com.tdoer.bedrock.context.ContextPath;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextApplicationInstallation extends ApplicationInstallation {

    /**
     * The path of the context (context type or context instance) in which the
     * application is installed
     *
     * @return Context path, must not be <code>null</code>
     */
    ContextPath getContextPath();

    /**
     * The Id of the client in which the application is installed
     * @return Client Id, must not be <code>null</code>
     */
    Long getClientId();

    /**
     * The Id of the tenant in which the application is installed
     *
     * @return Tenant Id, must not be <code>null</code>, but may be zero
     */
    Long getTenantId();
}
