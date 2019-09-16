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

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public enum SessionPolicy {

    /**
     * A user in a tenant client can only have one active session. If a user try to
     * login into a tenant client from different user agents (browsers), the later login
     * will kick off previous login.
     */
    KICK_OFF_PREVIOUS,

    /**
     * A user in a tenant client can only have one active session. If a user try to
     * login into a tenant client from different user agents (browsers), the later login
     * will be blocked by previous login and failed to get a new token.
     */
    BLOCK_NEWLY_COMING,

    /**
     * A user in a tenant client can have more than one active session, by login from
     * different user agent (browser).
     */
    ALLOW_ALL;

    private static final Map<String, SessionPolicy> mappings = new HashMap<>(3);

    static {
        for (SessionPolicy clientCategory : values()) {
            mappings.put(clientCategory.name(), clientCategory);
        }
    }


    /**
     * Resolve the given policy value to an {@code SessionPolicy}.
     *
     * @param policy the policy value as a String
     * @return the corresponding {@code SessionPolicy}, or {@code null} if not found
     */
    @Nullable
    public static SessionPolicy resolve(@Nullable String policy) {
        return (policy != null ? mappings.get(policy) : null);
    }


    /**
     * Determine whether this {@code SessionPolicy} matches the given
     * policy value.
     *
     * @param policy the policy value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String policy) {
        return (this == resolve(policy));
    }
}
