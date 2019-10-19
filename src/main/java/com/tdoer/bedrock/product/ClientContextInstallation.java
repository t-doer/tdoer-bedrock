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
package com.tdoer.bedrock.product;

import com.tdoer.bedrock.context.ContextInstallation;

import java.util.Locale;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-17
 */
public interface ClientContextInstallation extends ContextInstallation {

    /**
     * The Id of the client in which the context is installed
     * @return Client Id, it must not be <code>null</code>
     */
    Long getClientId();

    /**
     * The Id of the tenant, in which the context is installed specifically
     *
     * @return Tenant Id, it must not be <code>null</code>, but it may be zero.
     */
    Long getTenantId();

    /**
     * Default entry application code
     * @return Application code, it must not be blank
     */
    String getEntryApplicationCode();

    /**
     * Default entry nav item
     * @return Navigation item's node Id, it must not be blank
     */
    String getEntryNavItem();

    /**
     * Default entry language
     *
     * @return Language, it must not be <code>null</code>
     */
    Locale getEntryLanguage();
}
