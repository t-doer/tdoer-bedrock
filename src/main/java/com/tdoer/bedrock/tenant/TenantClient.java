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
package com.tdoer.bedrock.tenant;

import com.tdoer.bedrock.product.Client;

import java.util.List;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface TenantClient {
    /**
     * Tenant who accesses a product's client
     * @return Tenant, cannot be <code>null</code>
     */
    Tenant getTenant();

    /**
     * A product's client
     * @return Client, cannot be <code>null</code>
     */
    Client getClient();

    /**
     * Tenant Id
     * @return Tenant Id, cannot be <code>null</code>
     */
    Long getTenantId();

    /**
     * Client Id
     * @return Tenant Id, cannot be <code>null</code>
     */
    Long getClientId();

    /**
     * Client secret
     *
     * @return Client secret, cannot be null
     */
    String getSecret();

    /**
     * List a client's access domains for the tenant
     * @param list List to hold access domains, cannot be <code>null</code>
     */
    void listAccessDomains(List<String> list);
}
