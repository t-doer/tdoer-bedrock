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

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Product extends Serializable {
    /**
     * Product Id
     * @return Product Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Product code
     * @return Product code, must not be <code>null</code>
     */
    String getCode();

    /**
     * Product name
     * @return Product name, must not be <code>null</code>
     */
    String getName();

    /**
     * Product description
     *
     * @return Product description, may be <code>null</code>
     */
    String getDescription();

    /**
     * Product version
     * @return Product version, must not be <code>null</code>
     */
    String getVersion();

    /**
     * List the languages the product supports
     *
     * @param list List to hold languages
     */
    void listLanguages(List<Locale> list);

    /**
     * Get the product's client of specific ID
     *
     * @param clientId Client ID, cannot be <code>null</code>
     * @return Client if found, otherwise return <code>null</code>
     */
    Client getClient(Long clientId);

    /**
     * Get the product's client of specific client code
     * @param clientCode Client code, cannot be <code>null</code>
     * @return Client if found, otherwise return <code>null</code>
     */
    Client getClient(String clientCode);

    /**
     * List the product's enabled clients
     *
     * @param list List to hold clients, cannot be <code>null</code>
     */
    void listClients(List<Client> list);
}
