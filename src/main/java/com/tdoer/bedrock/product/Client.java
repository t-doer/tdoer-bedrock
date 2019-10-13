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
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Client {
    /**
     * Client Id
     *
     * @return
     */
    String getId();

    /**
     * Client name
     *
     * @return
     */
    String getName();

    /**
     * The product the client belongs to
     *
     * @return
     */
    Product getProduct();

    /**
     * Clent category
     *
     * @return
     */
    ClientCategory getCategory();

    /**
     * The client's access scopes
     *
     * @return
     */
    String[] getScopes();

    /**
     * The client's authorities or roles that it can plays as in its product
     *
     * @return
     */
    ClientRole[] getRoles();

    /**
     * Whether the client can be trusted or not
     *
     * @return
     */
    boolean isTrusted();


    /**
     * Client configuration
     *
     * @return
     */
    ClientConfig getClientConfig();

}
