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
package com.tdoer.bedrock.resource;

import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public enum ResourceType {
    PAGE, ACTION, NAVIGATION;

    private static final Map<String, ResourceType> mappings = new HashMap<>(3);

    static {
        for (ResourceType clientCategory : values()) {
            mappings.put(clientCategory.name(), clientCategory);
        }
    }


    /**
     * Resolve the given type value to an {@code ResourceType}.
     *
     * @param type the type value as a String
     * @return the corresponding {@code ResourceType}, or {@code null} if not found
     */
    @Nullable
    public static ResourceType resolve(@Nullable String type) {
        return (type != null ? mappings.get(type) : null);
    }


    /**
     * Determine whether this {@code ResourceType} matches the given
     * type value.
     *
     * @param type the type value as a String
     * @return {@code true} if it matches, {@code false} otherwise
     * @since 4.2.4
     */
    public boolean matches(String type) {
        return (this == resolve(type));
    }
}
