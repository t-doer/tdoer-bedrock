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

import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.service.Service;

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ApplicationRepository {
    /**
     * Get application of specific Id
     *
     * @param applicationId
     * @return
     */
    Application getApplication(String applicationId);

    /**
     * List pages of specific application which for the product, client, tenant, and context instance.
     *
     * @param applicationId
     * @param productId
     * @param clientId
     * @param tenantId
     * @param contextPath
     * @param list
     */
    void listPages(String applicationId, String productId, String clientId, Long tenantId, ContextPath contextPath, List<Page> list);

    /**
     * List actions of specific page which for the product, client, tenant, and context instance.
     *
     * @param pageId
     * @param applicationId
     * @param productId
     * @param clientId
     * @param tenantId
     * @param contextPath
     * @param list
     */
    void listActions(Long pageId, String applicationId, String productId, String clientId, Long tenantId, ContextPath contextPath, List<Action> list);

    /**
     * List services of specific service Id which for the product, client, tenant, and context instance.
     *
     * @param applicationId
     * @param productId
     * @param clientId
     * @param tenantId
     * @param contextPath
     * @param list
     */
    void listServices(String applicationId, String productId, String clientId, Long tenantId, ContextPath contextPath, List<Service> list);
}
