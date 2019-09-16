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

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public enum AccountStatus {
    ACTIVE, LOCKED, EXPIRED, DISABLED;

    private static final Map<String, AccountStatus> mappings = new HashMap<>(4);

    static {
        for (AccountStatus status : values()) {
            mappings.put(status.name(), status);
        }
    }


    /**
     * Resolve the given status value to an {@code AccountStatus}.
     *
     * @param status the status value as a String
     * @return the corresponding {@code AccountStatus}, or {@code null} if not found
     */
    @Nullable
    public static AccountStatus resolve(@Nullable String status) {
        return (status != null ? mappings.get(status) : null);
    }


    /**
     * Determine whether this {@code AccountStatus} matches the given
     * status value.
     *
     * @param status the status value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String status) {
        return (this == resolve(status));
    }
}
