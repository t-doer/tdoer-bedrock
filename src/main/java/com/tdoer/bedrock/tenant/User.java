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

import java.io.Serializable;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface User extends Serializable {

    /**
     * The user's ID
     *
     * @return The user's ID
     */
    Long getId();

    String getGuid();

    /**
     * The user's tenant ID. The tenant rent products to its users.
     *
     * @return The user's tenant ID, must not be blank
     */
    Long getTenantId();

    /**
     * The user's login account
     *
     * @return Login account, must not be blank
     */
    String getAccount();

    /**
     * The user's login password
     *
     * @return Login password
     */
    String getPassword();

    /**
     * The user's name, for example, "Htinker Hu"
     *
     * @return User name, must not be blank
     */
    String getName();

    /**
     * The user's email, for example, "htinker@163.com"
     *
     * @return User Email
     */
    String getEmail();

    /**
     * The user's mobile phone, for example, 13916723966
     *
     * @return Mobile phone number
     */
    String getPhone();


    /**
     * The user's status, for example, active, locked etc..
     *
     * @return
     */
    AccountStatus getStatus();

    /**
     * Credential or password status, for example, initial, expired, active etc.
     *
     * @return
     */
    CredentialStatus getCredentialStatus();

    /**
     * User category, say, b-end or c-end
     *
     * @return
     */
    String getCategory();

    /**
     * The user is real or fictional. When a user is not authenticated yet, the user may be fabricated,
     * played as a guest.
     *
     * @return true if a
     */
    boolean isReal();
}
