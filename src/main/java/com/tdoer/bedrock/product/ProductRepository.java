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
     * List all enabled products
     *
     * @param list List to hold products, cannot be <code>null</code>
     */
    void listProducts(List<Product> list);

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
     * Get all client of specific product
     *
     * @param productId Product Id, cannot be <code>null</code>
     * @return A product's all enabled clients
     */
    Client[] getClients(Long productId);
}
