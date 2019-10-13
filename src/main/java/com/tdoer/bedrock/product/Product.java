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
     *
     * @return Product Id
     */
    String getId();

    /**
     * Product name
     *
     * @return
     */
    String getName();

    /**
     * Product description
     *
     * @return
     */
    String getDescription();

    /**
     * List the languages the product supports
     *
     * @param list
     */
    void listLanguages(List<Locale> list);

    /**
     * Get the client of the product by the specific Id
     *
     * @param clientId
     * @return
     */
    Client getClient(String clientId);

    /**
     * List the product's clients
     *
     * @param list
     */
    void listClients(List<Client> list);
}
