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
package com.tdoer.bedrock.security;

import java.io.Serializable;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface CloudAuthenticationDetails extends Serializable {
    /**
     * Indicates the TCP/IP address the authentication request was received from.
     *
     * @return the address
     */
    String getRemoteAddress();

    /**
     * Indicates the <code>HttpSession</code> id the authentication request was received
     * from.
     *
     * @return the session ID
     */
    String getSessionId();

    int getRemotePort();

    String getUserAgent();

    String getClientId();

    Long getTenantId();

}
