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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.io.Serializable;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public final class ContextType implements Serializable {
    private Long type;

    private String code;

    private String category;

    private ContextType parent;

    // The 2 Bedrock framework inherent context types
    public static ContextType TENANT = new ContextType(1L, "TENANT", "TENANT");

    public static ContextType USER = new ContextType(2L, "USER", "USER", TENANT);

    private Logger logger = LoggerFactory.getLogger(ContextType.class);

    public ContextType(Long type, String code, String category) {
        this(type, code, category, null);
    }

    public ContextType(Long type, String code, String category, ContextType parent) {
        Assert.notNull(type, "Context type cannot be null");
        Assert.hasText(code, "Context code cannot be blank");
        Assert.hasText(category, "Context category cannot be blank");
        this.type = type;
        this.code = code;
        this.category = category;
        this.parent = parent;
    }

    /**
     * Context type
     * @return Context type, must not be <code>null</code>
     */
    public Long getType(){
        return type;
    }

    /**
     * Context code
     * @return Context code, must not be blank
     */
    public String getCode(){
        return code;
    }

    /**
     * Context category, say 'TENANT', 'USER', 'ORGANIZATION' etc.
     *
     * @return Context category, must not be blank
     */
    public String getCategory() {
        return category;
    }

    /**
     * Parent context type
     * @return Parent context type or <code>null</code> if the context type is the root context type
     */
    public ContextType getParentType(){
        return parent;
    }

    /**
     * Root context type, must be "TENANT" context type
     * @return Context type, must not be <code>null</code>
     */
    public ContextType getRootType(){
        ContextType t = this;
        while (t.getParentType() != null) {
            t = t.getParentType();
        }

        if(!t.equals(TENANT)){
            logger.error("Root context type is not 'TENANT', invalid context type: {}", this);
        }

        return t;
    }

    public boolean isUserContextType(){
        return (this.equals(USER));
    }

    public boolean isTenantContextType(){
        return (this.equals(TENANT));
    }

    /**
     * The path of the context
     * @return Context path, must not be <code>null</code>
     */
    public ContextPath getContextPath(){
        if(parent != null){
            return new ContextPath(type, 0L, parent.getContextPath());
        }else{
            return new ContextPath(type, 0L);
        }
    }

    @Override
    public int hashCode() {
        return code.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if(rhs == null){
            return false;
        }
        if(this == rhs){
            return true;
        }
        if (rhs instanceof ContextType) {
            ContextType t = (ContextType)rhs;
            return (type.equals(t.getType()));
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ContextType[");
        sb.append(type).append(", ");
        sb.append(code).append(", ");
        sb.append(category).append(", ");
        sb.append(getContextPath()).append("]");
        return sb.toString();
    }
}
