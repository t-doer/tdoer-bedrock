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

import java.io.Serializable;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextType extends Serializable {
    /**
     * Context type
     * @return Context type, must not be <code>null</code>
     */
    Long getType();

    /**
     * Context name
     * @return Context name, must not be blank
     */
    String getName();

    /**
     * Context code
     * @return Context code, must not be blank
     */
    String getCode();

    /**
     * Context category
     *
     * @return Context category, must not be blank
     */
    String getCategory();

    /**
     * Parent context type
     * @return Parent context type or <code>null</code> if the context type is the root context type
     */
    ContextType getParent();

    /**
     * Children context types
     * @return Context types or <code>null</code> if the context type is the leaf node
     */
    ContextType[] getChildren();

    /**
     * Root context type, must be "TENANT" context type
     * @return Context type, must not be <code>null</code>
     */
    ContextType getRoot();

    /**
     * Find context type of specific type
     * @param contextType Context type, cannot be <code>null</code>
     * @return Context type or <code>null</code> it not found
     */
    ContextType find(Long contextType);

    /**
     * The path of the context
     * @return Context path, must not be <code>null</code>
     */
    ContextPath getContextPath();

    /**
     * Check if the context is the root context type, that's "TENANT"?.
     * @return true if it's the  root context type
     */
    boolean isRoot();
}
