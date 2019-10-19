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
package com.tdoer.bedrock.application;

import com.tdoer.bedrock.resource.Resource;
import com.tdoer.bedrock.resource.ResourceCategory;
import com.tdoer.bedrock.service.ServiceMethod;

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ApplicationResource extends Resource {

    @Override
    default ResourceCategory getCategory() {
        return ResourceCategory.APPLICATION;
    }

    /**
     * The Id of the Application to which the resource belongs
     *
     * @return Application Id, never be null
     */
    Long getApplicationId();

    /**
     * List the service methods which the application resource associates with
     *
     * @param list List to hold service methods, cannot be <code>null</code>
     */
    void listServiceMethods(List<ServiceMethod> list);
}
