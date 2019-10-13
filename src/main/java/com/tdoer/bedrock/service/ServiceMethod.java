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

import com.tdoer.bedrock.context.ContextPath;
import org.springframework.http.HttpMethod;

import java.io.Serializable;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ServiceMethod extends Serializable {

    /**
     * Get the service methond's Id
     *
     * @return
     */
    Long getId();

    /**
     * The Id of the service who provides the service method
     *
     * @return
     */
    String getServiceId();

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
     * Check if given http method and path matches the provider method
     *
     * @param httpMethod
     * @param path
     * @return
     */
    boolean match(String httpMethod, String path);

    /**
     * 应用ID，是扩展属性，如果非空，就意味着该服务方法只能开放给指定的应用，否则不限定，可以开放给
     * 所有应用；
     *
     * @return 应用ID，可能为{@code null}
     */
    String getApplicationId();

    /**
     * Product Id, to which the service method belongs
     *
     * @return Product Id, it may be {@code Null}
     */
    String getProductId();

    /**
     * Tenant Id, to which the service method belongs to
     *
     * @return Tenant Id, it may be {@code Null}
     */
    Long getTenantId();

    /**
     * Client Id, to which the service method belongs to
     *
     * @return Client Id, it may be {@code Null}
     */
    String getClientId();

    /**
     * Context path, to which the service method belongs to
     *
     * @return Context path, it may be {@code Null}
     */
    ContextPath getContextPath();

}
