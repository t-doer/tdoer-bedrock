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
public interface TokenConfig {
    /**
     * Client Id
     * @return Client Id, must not be <code>null</code>
     */
    Long getClientId();

    /**
     * Tenant Id
     * @return Tenant Id, must not be <code>null</code>
     */
    Long getTenantId();

    /**
     * The client's grant types, for example, authorization_code, password etc.
     *
     * @return token grant type, must not be empty
     */
    String[] getGrantTypes();

    /**
     * The client's auto approval's scope
     *
     * @return Auto approval, must not be empty
     */
    String[] getAutoApprovals();

    /**
     * The redirection URI once user's access token is granted
     * @return Redirection URI, may be blank
     */
    String getWebRedirectURI();

    /**
     * Access token's validity duration in seconds.
     *
     * @return Validity duration, must not be <code>null</code>
     */
    Integer getAccessTokenValidityInSeconds();

    /**
     * Refresh token's validity duration in seconds.
     *
     * @return Validity duration, must not be <code>null</code>
     */
    Integer getRefreshTokenValidityInSeconds();

    /**
     * Session policy
     *
     * @return Session policy, must not be <code>null</code>
     */
    SessionPolicy getSessionPolicy();
}
