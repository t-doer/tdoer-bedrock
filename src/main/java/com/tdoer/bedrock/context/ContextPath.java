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
public final class ContextPath implements Serializable {

    private Integer type;

    private Long instanceId;

    private ContextPath parent;

    public ContextPath(Integer type, Long instanceId) {
        this(type, instanceId, null);
    }

    public ContextPath(Integer type, Long instanceId, ContextPath parent) {
        this.type = type;
        this.instanceId = instanceId;
        this.parent = parent;
    }

    public String getValue() {
        return type + "." + instanceId;
    }

    public String getAbsoluteValue() {
        StringBuilder buffer = new StringBuilder();
        if (parent != null) {
            buffer.append(parent.getAbsoluteValue());
            buffer.append("-");
        }
        buffer.append(getValue());

        return buffer.toString();
    }

    public void setParentPath(ContextPath parent) {
        this.parent = parent;
    }

    public ContextPath getParentPath() {
        return parent;
    }

    public ContextPath getRootPath() {
        ContextPath c = this;
        while (c.getParentPath() != null) {
            c = c.getParentPath();
        }
        return c;
    }

    public boolean isRootPath(){
        return (parent == null);
    }

    public Integer getType() {
        return type;
    }

    public Long getInstanceId() {
        return instanceId;
    }

    public ContextPath getParent() {
        return parent;
    }

    public ContextPath clone() {
        if (parent != null) {
            return new ContextPath(type, instanceId, parent.clone());
        } else {
            return new ContextPath(type, instanceId);
        }
    }

    /**
     * The method is mainly used to search configurations for a context, if no next
     * lookup any more, it will return itself. Take Context "22.1-23.1" for example,
     * the method will return the contexts in sequence:
     * <ol>
     * <li>22.1-23.1</li>
     * <li>22.1-23.0</li>
     * <li>22.0-23.0</li>
     * </ol>
     *
     * @return Next lookup context or itself if no more
     */
    public ContextPath parentTemplate() {
        if (!instanceId.equals(0L)) {
            return new ContextPath(type, 0L, parent);
        } else {
            if (parent != null) {
                return new ContextPath(type, instanceId, parent.parentTemplate());
            } else {
                return this;
            }
        }
    }

    @Override
    public int hashCode() {
        return getAbsoluteValue().hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
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
