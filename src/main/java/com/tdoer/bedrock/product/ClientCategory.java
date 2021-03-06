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
public enum ClientCategory {
    B_END, C_END;

    private static final Map<String, ClientCategory> mappings = new HashMap<>(2);

    static {
        for (ClientCategory clientCategory : values()) {
            mappings.put(clientCategory.name(), clientCategory);
        }
    }


    /**
     * Resolve the given category value to an {@code ClientCategory}.
     *
     * @param category the category value as a String
     * @return the corresponding {@code ClientCategory}, or {@code null} if not found
     */
    @Nullable
    public static ClientCategory resolve(@Nullable String category) {
        return (category != null ? mappings.get(category) : null);
    }


    /**
     * Determine whether this {@code ClientCategory} matches the given
     * category value.
     *
     * @param category the category value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String category) {
        return (this == resolve(category));
    }
}
