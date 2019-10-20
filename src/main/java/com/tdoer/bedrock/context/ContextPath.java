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

import org.springframework.util.Assert;

import java.io.Serializable;
/**
 * @Description The path of context (context type or context instance). Context is a
 * hierarchy tree. Context path consists of "level1 node -level2 node - leve3 node - ...",
 * each node's path is of the format "type.instanceId", containing context type and instance
 * information. The root node must be a tenant. So context path looks like this "1.1-2.1-3.1"
 * If "instanceId" is zero, a context path must be a context type's path.
 *
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public final class ContextPath implements Serializable {

    /**
     * Context type
     */
    private Long type;

    /**
     * Instance Id, cant be <code>zero</code>
     */
    private Long instanceId;

    /**
     * Parent node's context path
     */
    private ContextPath parent;

    public ContextPath(Long type, Long instanceId) {
        this(type, instanceId, null);
    }

    public ContextPath(Long type, Long instanceId, ContextPath parent) {
        Assert.notNull(type, "Context type cannot be null");
        Assert.notNull(instanceId, "Instance Id cannot be null");
        this.type = type;
        this.instanceId = instanceId;
        this.parent = parent;
    }

    /**
     * Context type
     * @return Context type, must not be <code>null</code>
     */
    public Long getType() {
        return type;
    }

    /**
     * Instance Id, if the path is a context type's path, instance Id is zero.
     *
     * @return Instance Id, must not be <code>null</code>
     */
    public Long getInstanceId() {
        return instanceId;
    }

    /**
     * Parent context's path
     * @return Parent path or <code>null</code> if current node is the root node
     */
    public ContextPath getParent() {
        return parent;
    }

    /**
     * Current node's path value, say, "2.1"
     * @return Path value, must not be blank
     */
    public String getValue() {
        return type + "." + instanceId;
    }

    /**
     * Current node's absolute path value, say "1.1-2.1"
     * @return Absolute path value, must not be blank
     */
    public String getAbsoluteValue() {
        StringBuilder buffer = new StringBuilder();
        if (parent != null) {
            buffer.append(parent.getAbsoluteValue());
            buffer.append("-");
        }
        buffer.append(getValue());

        return buffer.toString();
    }

    /**
     * Set parent path
     * @param parent Parent path, can be <code>null</code>
     */
    public void setParentPath(ContextPath parent) {
        this.parent = parent;
    }

    /**
     * Get current node's parent node's path
     * @return Parent path or <code>null</code> if current node is the root node
     */
    public ContextPath getParentPath() {
        return parent;
    }

    /**
     * Get the root node's path, that's, "TENANT" node's path
     * @return Root path, must not be <code>null</code>
     */
    public ContextPath getRootPath() {
        ContextPath c = this;
        while (c.getParentPath() != null) {
            c = c.getParentPath();
        }
        return c;
    }

    /**
     * Check if current node is the root node, that's, "TENANT" node
     * @return
     */
    public boolean isRootPath(){
        return (parent == null);
    }

    /**
     * Clone the whole context path
     * @return Context path with the same absolute path value
     */
    public ContextPath clone() {
        if (parent != null) {
            return new ContextPath(type, instanceId, parent.clone());
        } else {
            return new ContextPath(type, instanceId);
        }
    }

    /**
     * The method is mainly used to search configurations for a context, if no next
     * lookup any more, it will return <code>null</code>. Take Context "22.1-23.1" for example,
     * the method will return the context path in sequence if it's called recursively:
     * <ol>
     * <li>22.1-23.1</li>
     * <li>22.1-23.0</li>
     * <li>22.0-23.0</li>
     * <li>null</li>
     * </ol>
     *
     * @return Next lookup context or itself if no more
     */
    public ContextPath nextLookup() {
        if (!instanceId.equals(0L)) {
            return new ContextPath(type, 0L, parent);
        } else {
            if (parent != null) {
                return new ContextPath(type, instanceId, parent.nextLookup());
            } else {
                return null;
            }
        }
    }

    @Override
    public int hashCode() {
        return getAbsoluteValue().hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if(rhs == null){
            return false;
        }
        if(this == rhs){
            return true;
        }
        if (rhs instanceof ContextPath) {
            return (this.getAbsoluteValue().equals(((ContextPath) rhs).getAbsoluteValue()));
        }
        return false;
    }

    @Override
    public String toString() {
        return getAbsoluteValue();
    }
}
