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
package com.tdoer.bedrock.context;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface RootContextType {
    /**
     * The root context, that's "TENANT" context type
     * @return Context type, must not be <code>null</code>
     */
    ContextType getRoot();

    /**
     * Find the context of specific type
     * @param type Type value, cannot be <code>null</code>
     * @return Context type or <code>null</code> if not found
     */
    ContextType find(Long type);

    /**
     * Find the context of specific context code
     * @param code Context code, cannot be blank
     * @return Context type or <code>null</code> if not found
     */
    ContextType find(String code);
}
