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

import java.io.Serializable;
import java.util.List;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Service extends Serializable {

    /**
     * Service Id
     *
     * @return Service Id
     */
    String getId();

    /**
     * Service provider's name
     *
     * @return
     */
    String getProvider();

    /**
     * Service name
     *
     * @return Service name
     */
    String getName();

    /**
     * Service description
     *
     * @return Service description, maybe be {@code Null}
     */
    String getDescription();

    /**
     * Service author's name
     *
     * @return Service author's name, may be {@code Null}
     */
    String getAuthor();

    /**
     * Service maintainers, user names delimited by comma
     *
     * @return Service maintainers, may be {@code Null}
     */
    String getMaintainers();

    /**
     * Service version
     *
     * @return Service version, maybe be {@code Null}
     */
    String getVersion();

    /**
     * List available service methods in current environment {@link com.tdoer.bedrock.CloudEnvironment},
     * that's, list available service method according to current client, tenant and context instance.
     * <br>
     * Service methods will be appended to the given list.
     *
     * @param list List to hold service methods.
     */
    void listCurrentMethods(List<ServiceMethod> list);


    /**
     * Get service method of specific Id available in the service
     *
     * @param methodId
     * @return Service method if found, otherwise {@code null}
     */
    ServiceMethod getMethod(Long methodId);

}
