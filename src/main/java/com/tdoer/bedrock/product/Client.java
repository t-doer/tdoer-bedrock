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
     * @return Client Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Client code
     * @return Client code, must not be blank
     */
    String getCode();

    /**
     * Client name
     *
     * @return Client name, must not be blank
     */
    String getName();

    /**
     * The Id of the product to which the client belongs
     *
     * @return Product Id, must not be <code>null</code>
     */
    Long getProductId();

    /**
     * Clent category, B_END or C_END
     *
     * @return Client category, must not be <code>null</code>
     */
    ClientCategory getCategory();

    /**
     * The client's access scopes
     *
     * @return The client's access scopes
     */
    String[] getScopes();

    /**
     * The client's roles that it can plays to services
     *
     * @return Client's roles
     */
    ClientRole[] getRoles();

    /**
     * Whether the client can be trusted or not. If a client program
     * is developed by 3rd-party, usually the client should not be trusted,
     * and conduct strict access policy
     *
     * @return true if the client is trusted
     */
    boolean isTrusted();


    /**
     * Client configuration, for example, installed applications, services,
     * and access token configuration.
     *
     * @return Client configuration, must not be <code>null</code>
     */
    ClientConfig getClientConfig();

}
