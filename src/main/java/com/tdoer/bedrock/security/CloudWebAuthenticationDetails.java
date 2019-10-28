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

import com.tdoer.bedrock.CloudEnvironment;
import com.tdoer.bedrock.CloudEnvironmentHolder;
import com.tdoer.springboot.util.NetworkUtil;
import org.springframework.security.core.SpringSecurityCoreVersion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class CloudWebAuthenticationDetails implements CloudAuthenticationDetails {

    private static final long serialVersionUID = SpringSecurityCoreVersion.SERIAL_VERSION_UID;

    // ~ Instance fields
    // ================================================================================================

    private final String remoteAddress;
    private final int remotePort;
    private final String sessionId;
    private final String userAgent;
    private final Long tenantId;
    private final String clientId;

    // ~ Constructors
    // ===================================================================================================

    /**
     * Records the remote address and will also set the session Id if a session already
     * exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public CloudWebAuthenticationDetails(HttpServletRequest request) {
        this.remoteAddress = NetworkUtil.getRemoteAddr(request);
        this.remotePort = request.getRemotePort();
        this.userAgent = request.getHeader("user-agent");
        HttpSession session = request.getSession(false);
        this.sessionId = (session != null) ? session.getId() : null;

        CloudEnvironment env = CloudEnvironmentHolder.getEnvironment();
        this.tenantId = env.getTenantId();
        this.clientId = env.getTenantClient().getGuid();
    }

    /**
     * Constructor to add Jackson2 serialize/deserialize support
     *
     * @param remoteAddress remote address of current request
     * @param sessionId     session id
     */
    private CloudWebAuthenticationDetails(final String remoteAddress, final int remotePort, final String userAgent,
                                          final String sessionId, final Long tenantId, final String clientId) {
        this.remoteAddress = remoteAddress;
        this.remotePort = remotePort;
        this.userAgent = userAgent;
        this.sessionId = sessionId;
        this.clientId = clientId;
        this.tenantId = tenantId;
    }

    // ~ Methods
    // ========================================================================================================

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;


        CloudWebAuthenticationDetails rhs = (CloudWebAuthenticationDetails) obj;

        if ((remoteAddress == null) && (rhs.getRemoteAddress() != null)) {
            return false;
        }

        if ((remoteAddress != null) && (rhs.getRemoteAddress() == null)) {
            return false;
        }

        if (remoteAddress != null) {
            if (!remoteAddress.equals(rhs.getRemoteAddress())) {
                return false;
            }
        }

        if (remotePort != rhs.getRemotePort()) {
            return false;
        }

        if ((userAgent == null) && (rhs.getUserAgent() != null)) {
            return false;
        }

        if ((userAgent != null) && (rhs.getUserAgent() == null)) {
            return false;
        }

        if (userAgent != null) {
            if (!userAgent.equals(rhs.getUserAgent())) {
                return false;
            }
        }

        if ((sessionId == null) && (rhs.getSessionId() != null)) {
            return false;
        }

        if ((sessionId != null) && (rhs.getSessionId() == null)) {
            return false;
        }

        if (sessionId != null) {
            if (!sessionId.equals(rhs.getSessionId())) {
                return false;
            }
        }

        if ((clientId == null) && (rhs.getClientId() != null)) {
            return false;
        }

        if ((clientId != null) && (rhs.getClientId() == null)) {
            return false;
        }

        if (clientId != null) {
            if (!clientId.equals(rhs.getClientId())) {
                return false;
            }
        }

        if ((tenantId == null) && (rhs.getTenantId() != null)) {
            return false;
        }

        if ((tenantId != null) && (rhs.getTenantId() == null)) {
            return false;
        }

        if (tenantId != null) {
            if (!tenantId.equals(rhs.getTenantId())) {
                return false;
            }
        }

        return true;
    }

    /**
     * Indicates the TCP/IP address the authentication request was received from.
     *
     * @return the address
     */
    public String getRemoteAddress() {
        return remoteAddress;
    }

    /**
     * Indicates the <code>HttpSession</code> id the authentication request was received
     * from.
     *
     * @return the session ID
     */
    public String getSessionId() {
        return sessionId;
    }

    public int getRemotePort() {
        return remotePort;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public String getClientId() {
        return clientId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    @Override
    public int hashCode() {
        int code = 7654;

        if (this.remoteAddress != null) {
            code = code * (this.remoteAddress.hashCode() % 7);
        }

        code = code * (this.remotePort % 7);

        if (this.userAgent != null) {
            code = code * (this.userAgent.hashCode() % 7);
        }

        if (this.sessionId != null) {
            code = code * (this.sessionId.hashCode() % 7);
        }

        if (this.clientId != null) {
            code = code * (this.clientId.hashCode() % 7);
        }

        if (this.tenantId != null) {
            code = code * (this.tenantId.hashCode() % 7);
        }

        return code;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("RemoteIpAddress: ").append(this.getRemoteAddress()).append("; ");
        sb.append("RemotePort: ").append(this.getRemotePort()).append("; ");
        sb.append("SessionId: ").append(this.getSessionId()).append("; ");
        sb.append("ClientId: ").append(this.getClientId()).append("; ");
        sb.append("TenantId: ").append(this.getTenantId()).append("; ");
        sb.append("UserAgent: ").append(this.getUserAgent()).append("; ");

        return sb.toString();
    }
}

