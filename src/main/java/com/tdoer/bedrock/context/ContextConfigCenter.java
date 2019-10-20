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

import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextConfigCenter {

    /**
     * List user roles of specific user Id in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param userId User Id, cannot be <code>null</code>
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listUserRoles(Long tenantId, ContextPath contextPath, Long userId, List<ContextRole> list);

    /**
     * List public authorities in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold public authorities, cannot be <code>null</code>
     */
    void listPublicAuthorities(Long tenantId, ContextPath contextPath, List<PublicAuthority> list);

    /**
     * List context roles in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listContextRoles(Long tenantId, ContextPath contextPath, List<ContextRole> list);

    /**
     * Get context role of specific role Id in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param roleId Role Id, cannot be <code>null</code>
     * @return Context role or <code>null</code>
     */
    ContextRole getContextRole(Long tenantId, ContextPath contextPath, Long roleId);

    /**
     * List application installations which are in specific client and specific tenant's
     * specific context instance
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param list List to hold {@link ContextApplicationInstallation}, cannot be <code>null</code>
     */
    void listApplicationInstallation(Long tenantId, ContextPath contextPath, Long clientId,
                                     List<ContextApplicationInstallation> list);

    /**
     * Get application installation of specific application Id which is in specific client and
     * specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param applicationId Application Id, cannot be <code>null</code>
     * @return Application installation or <code>null</code>
     */
    ContextApplicationInstallation getApplicationInstallation(Long tenantId, ContextPath contextPath, Long clientId,
                                                              String applicationId);
}
