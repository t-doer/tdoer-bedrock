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

import java.util.List;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface RentalCenter {
    /**
     * Get tenant of specific tenant code
     * @param tenantCode Tenant code, cannot be blank
     * @return Tenant if found
     * @throws TenantNotFoundException if not found
     */
    Tenant getTenantByCode(String tenantCode) throws TenantNotFoundException;

    /**
     * Get tenant of specific tenant Id
     * @param tenantId Tenant Id, cannot be blank
     * @return Tenant if found
     * @throws TenantNotFoundException if not found
     */
    Tenant getTenantById(Long tenantId) throws TenantNotFoundException;

    /**
     * Get tenant of specific tenant GUID
     * @param guid Tenant GUID, cannot be blank
     * @return Tenant if found
     * @throws TenantNotFoundException if not found
     */
    Tenant getTenantByGUID(String guid) throws TenantNotFoundException;

    /**
     * Find product rental of tenant Id and product Id
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param productId Product Id, cannot be <code>null</code>
     * @return Product rental or <code>null</code> if not found
     */
    ProductRental getProductRendtal(Long tenantId, Long productId);

    /**
     * List all product rentals of a tenant
     * @param tenantId Tenant Id, cannot be <code>nul</code>
     * @return List of product rental or <code>null</code>
     */
    void listProductRentals(Long tenantId, List<ProductRental> list);

    /**
     * Find the tenant client of specific access domain
     * @param accessDomain
     * @return Tenant client or <code>null</code> if not found
     */
    TenantClient getTenantClient(String accessDomain);

    /**
     * Get tenant client of specific tenant and client
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @return
     */
    TenantClient getTenantClient(Long tenantId, Long clientId);

    /**
     * Get all tenant clients of a tenant
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @return List of tenant client or <code>null</code>
     */
    void listTenantClients(Long tenantId, List<TenantClient> list);
}
