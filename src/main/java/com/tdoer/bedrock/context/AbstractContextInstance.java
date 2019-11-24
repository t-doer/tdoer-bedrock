/*
 * Copyright 2019 T-Doer (tdoer.com).
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
 *
 */
package com.tdoer.bedrock.context;

import com.tdoer.bedrock.Platform;
import org.springframework.util.Assert;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-11-24
 */
public abstract class AbstractContextInstance implements ContextInstance{
    protected ContextType contextType;

    protected ContextPath contextPath;

    public AbstractContextInstance(ContextType contextType, ContextPath contextPath) {
        Assert.notNull(contextType, "Context type cannot be null");
        Assert.notNull(contextPath, "Context path cannot be null");

        this.contextType = contextType;
        this.contextPath = contextPath;
    }

    /**
     * Parent context instance, that's, the tenant to which the user belongs
     *
     * @return the tenant to which the user belongs, must not be <code>null</code>
     */
    @Override
    public ContextInstance getParent() {
        if(contextPath.getParent() != null){
            ContextPath parentPath = contextPath.getParent();
            return Platform.getContextCenter().getContextInstance(getTenantId(), parentPath.getType(), parentPath.getInstanceId());
        }else{
            return null;
        }
    }

    /**
     * The top parent of the instance, that's, the tenant to which the user belongs
     *
     * @return the tenant to which the user belongs, must not be <code>null</code>
     */
    @Override
    public ContextInstance getTopParent() {
        ContextPath rootPath = contextPath.getRootPath();
        return Platform.getContextCenter().getContextInstance(getTenantId(), rootPath.getType(), rootPath.getInstanceId());
    }

    /**
     * Context path to the context instance, say, '1.1-20.2-30.3', it's always
     * globally unique.
     *
     * @return Context path, must not be <code>null</code>
     */
    @Override
    public ContextPath getContextPath() {
        return contextPath;
    }

    /**
     * Context type of the context instance. An instance must below to only one
     * context type.
     *
     * @return Context type, must not be <code>null</code>
     */
    @Override
    public ContextType getContextType() {
        return contextType;
    }

    /**
     * The instance's configurations, for example, available applications, context roles etc.
     *
     * @return Context configuration, must not be <code>null</code>
     */
    @Override
    public ContextConfig getContextConfig() {
        return Platform.getContextCenter().getContextConfig(this);
    }

}
