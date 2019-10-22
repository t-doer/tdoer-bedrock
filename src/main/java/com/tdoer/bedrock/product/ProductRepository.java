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
package com.tdoer.bedrock.product;

import com.tdoer.bedrock.context.ContextPath;

import java.util.List;

/**
 * @Description Product repository holds all products and their clients.
 *
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ProductRepository {
    /**
     * Get product of specific Id
     *
     * @param productId Product Id, cannot be <code>null</code>
     * @return Product if found
     * @throws ProductNotFoundException if the product dose not exist or is disabled
     */
    Product getProduct(Long productId) throws ProductNotFoundException;

    /**
     * Get product of specific product code
     * @param productCode Product code, cannot be <code>null</code>
     * @return Product if found
     * @throws ProductNotFoundException if the product dose not exist or is disabled
     */
    Product getProduct(String productCode) throws ProductNotFoundException;

    /**
     * Get specific client of specific product
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @return Client if found
     * @throws ClientNotFoundException if the client dose not exist or is disabled
     */
    Client getClient(Long productId, Long clientId) throws ClientNotFoundException;

    /**
     * Get specific client of specific product
     * @param productId Product Id, cannot be <code>null</code>
     * @param clientCode Client code, cannot be <code>null</code>
     * @return Client if found
     * @throws ClientNotFoundException if the client dose not exist or is disabled
     */
    Client getClient(Long productId, String clientCode) throws ClientNotFoundException;

    /**
     * List all client of specific product
     *
     * @param productId Product Id, cannot be <code>null</code>
     * @param list List to hold clients, cannot be <code>null</code>
     */
    void listClients(Long productId, List<Client> list);

    /**
     * The application installation of specific application in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param applicationId Application Id, cannot be <code>null</code>
     * @return {@link ClientApplicationInstallation} if it exists or is enabled, otherwise return <code>null</code>
     */
    ClientApplicationInstallation getApplicationInstallation(Long clientId, Long tenantId, Long applicationId);

    /**
     * List application installations which are installed in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param list List to hold application installations, cannot be <code>null</code>
     */
    void listApplicationInstallations(Long clientId, Long tenantId, List<ClientApplicationInstallation> list);

    /**
     * Get the service installation of specific service in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param serviceId Service Id, cannot be <code>null</code>
     * @return {@link ClientServiceInstallation} if it exists or is enabled, otherwise return <code>null</code>
     */
    ClientServiceInstallation getClientServiceInstallation(Long clientId, Long tenantId, Long serviceId);

    /**
     * List service installations which are installed in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param list List to hold service installations, cannot be <code>null</code>
     */
    void listClientServiceInstallations(Long clientId, Long tenantId, List<ClientServiceInstallation> list);

    /**
     * Get the context (context type or context instance) installation of specific context path in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param contextPath Context path, cannot be <code>null</code>
     * @return {@link ClientContextInstallation} if it exists or is enabled, otherwise return <code>null</code>
     */
    ClientContextInstallation getContextInstallation(Long clientId, Long tenantId, ContextPath contextPath);

    /**
     * List context installations which are installed in the tenant's client
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @param list List to hold context installations, cannot be <code>null</code>
     */
    void listContextInstallations(Long clientId, Long tenantId, List<ClientContextInstallation> list);

    /**
     * The token configuration
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>, but can be zero
     * @return The token configuration, must not be <code>null</code>
     */
    TokenConfig getTokenConfig(Long clientId, Long tenantId);
}
