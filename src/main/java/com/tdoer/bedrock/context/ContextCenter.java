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

import com.tdoer.bedrock.service.ServiceMethod;

import javax.naming.Context;
import java.util.List;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextCenter {

    /**
     * Get the root context type for specific tenant
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @return Context type, must not be <code>null</code>
     */
    ContextType getRootContextType(Long tenantId);

    /**
     * List all context types defined by the tenant.
     *
     * Note the list should not include "TENANT" context type which is
     * the root context type for all.
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param list List to hold a tenant's context types.
     */
    void listContextTypes(Long tenantId, List<ContextType> list);

    /**
     * Get specific context type available for specific tenant
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextType Context type, cannot be <code>null</code>
     * @return Context type or <code>null</code>
     */
    ContextType getContextType(Long tenantId, Long contextType);

    /**
     * Get specific context type available for specific tenant
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextCode Context code, cannot be blank
     * @return Context type or <code>null</code>
     */
    ContextType getContextType(Long tenantId, String contextCode);

    /**
     * Get context instance of specific context path in specific tenant
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path, cannot be <code>null</code>
     * @return Context instance if it exists and is enabled
     * @throws ContextInstanceNotFoundException if it is not found
     */
    ContextInstance getContextInstance(Long tenantId, ContextPath contextPath) throws ContextInstanceNotFoundException;

    /**
     * Get context instance of specific context path in specific tenant
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param guid Context instance GUID, cannot be blank
     * @return Context instance if it exists and is enabled
     * @throws ContextInstanceNotFoundException if it is not found
     */
    ContextInstance getContextInstance(Long tenantId, String guid) throws ContextInstanceNotFoundException;

    /**
     * Get context instance of specific context path in specific tenant
     *
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param instanceId Context instance Id, cannot be <code>null</code>
     * @return Context instance if it exists and is enabled
     * @throws ContextInstanceNotFoundException if it is not found
     */
    ContextInstance getContextInstance(Long tenantId, Long instanceId) throws ContextInstanceNotFoundException;

    /**
     * List user roles of specific user Id in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param userId User Id, cannot be <code>null</code>
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listUserRoles(Long tenantId, ContextPath contextPath, Long userId, List<ContextRole> list);

    /**
     * List context roles in specific tenant's specific context instance
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listContextRoles(Long tenantId, ContextPath contextPath, List<ContextRole> list);

    /**
     * List application installations which are in specific client and specific tenant's
     * specific context instance
     *
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold {@link ContextApplicationInstallation}, cannot be <code>null</code>
     */
    void listApplicationInstallation(Long clientId, Long tenantId, ContextPath contextPath,
                                     List<ContextApplicationInstallation> list);

    /**
     * List public resources in specific tenant's specific context instance
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold public client resources, cannot be <code>null</code>
     */
    void listPublicResources(Long clientId, Long tenantId, ContextPath contextPath, List<ClientResource> list);

    /**
     * List public service methods in specific tenant's specific context instance
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code
     * @param list List to hold public service methods, cannot be <code>null</code>
     */
    void listPublicMethods(Long clientId, Long tenantId, ContextPath contextPath, List<ServiceMethod> list);

    /**
     * List resource authorities of specific role in specific tenant's specific context instance
     * @param roleId Role Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code>
     * @param list List to hold public client resources, cannot be <code>null</code>
     */
    void listRoleResources(Long roleId, Long clientId, Long tenantId, ContextPath contextPath,
                           List<ClientResource> list);

    /**
     * List service method authorities of specific role in specific tenant's specific context instance
     * @param roleId Role Id, cannot be <code>null</code>
     * @param clientId Client Id, cannot be <code>null</code>
     * @param tenantId Tenant Id, cannot be <code>null</code>
     * @param contextPath Context path of context instance, cannot be <code>null</code
     * @param list List to hold public service methods, cannot be <code>null</code>
     */
    void listRoleMethods(Long roleId, Long clientId, Long tenantId, ContextPath contextPath,
                           List<ServiceMethod> list);
}
