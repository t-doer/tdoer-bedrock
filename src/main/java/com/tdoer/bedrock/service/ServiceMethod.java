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
     * @return Service method's Id, must not be <code>null</code>
     */
    Long getId();

    /**
     * Get the Id of the service which provides the service method
     *
     * @return Service Id, must not be <code>null</code>
     */
    Long getServiceId();

    /**
     * Get service method's name
     *
     * @return Service method's name, must not be blank
     */
    String getName();

    /**
     * Get the method's HTTP method, for example, POST, GET, DELETE, UPDATE etc..
     *
     * @return HTTP method, must not be <code>null</code>
     */
    HttpMethod getHttpMethod();

    /**
     * Get the methods'a request URI, which following ant path pattern, for example,
     * "/user/*", "/user/**" etc.
     *
     * @return Method URI, must not be blank
     */
    String getURI();

    /**
     * Whether the service method is a customized one or not. A customized service
     * method can only be available for some cloud environment's elements.
     *
     * @return true if the method is a customized one
     */
    boolean isCustomized();
    /**
     * Check if the service method matches given http method and uri
     *
     * @param httpMethod HTTP method, for example, POST, GET etc.
     * @param requestURI Request URI
     * @return true if matches
     */
    boolean match(String httpMethod, String requestURI);
}
