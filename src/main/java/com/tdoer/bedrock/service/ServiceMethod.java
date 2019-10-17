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
package com.tdoer.bedrock.service;

import com.tdoer.bedrock.resource.Resource;
import com.tdoer.bedrock.resource.ResourceCategory;
import com.tdoer.bedrock.resource.ResourceType;
import org.springframework.http.HttpMethod;

import java.io.Serializable;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ServiceMethod extends Resource {
    @Override
    default ResourceCategory getCategory(){
        return ResourceCategory.SERVICE;
    }

    @Override
    default ResourceType getType() {
        return ResourceType.SERVICE_METHOD;
    }

    /**
     * Get the service methond's Id
     *
     * @return
     */
    Long getId();

    /**
     * The Id of the service which provides the service method
     *
     * @return
     */
    Long getServiceId();

    /**
     * Get provider method's name
     *
     * @return
     */
    String getName();

    /**
     * Get provider method's HTTP method, for example, POST, GET, DELETE, UPDATE etc..
     *
     * @return
     */
    HttpMethod getHttpMethod();

    /**
     * Get provider methods'a request URI
     *
     * @return
     */
    String getURI();

    /**
     * Whether the service method is a customized one or not.
     *
     * @return
     */
    boolean isCustomized();
    /**
     * Check if given http method and path matches the service method
     *
     * @param httpMethod
     * @param path
     * @return
     */
    boolean match(String httpMethod, String path);
}
