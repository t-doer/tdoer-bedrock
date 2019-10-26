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

import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.service.ServiceMethod;

import java.util.List;
import java.util.Locale;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextConfig {

    /**
     * List current user's roles in the context instance. Current user
     * is the user who is sending the request, if the user dose not log
     * in yet, current user is <code>null</code>.
     *
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listCurrentUserRoles(List<ContextRole> list);

    /**
     * List context roles of specific user Id
     *
     * @param userId User Id, must not be <code>null</code>
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listUserRoles(Long userId, List<ContextRole> list);


    /**
     * List all enabled roles in current context instance, including
     * system ones and custom ones
     *
     * @param list List to hold context roles, cannot be <code>null</code>
     */
    void listContextRoles(List<ContextRole> list);

    /**
     * Get context role of specific role Id, which is available in the context instance
     *
     * @param roleId Role Id, cannot be <code>null</code>
     * @return Context role if it exists and is enabled and belows to the context,
     * otherwise return <code>null</code>
     */
    ContextRole getContextRole(Long roleId);

    /**
     * Get context role of specific role code, which is available in the context instance.
     *
     * @param roleCode Role code, cannot be blank
     * @return Context role if it exists and is enabled and belows to the context,
     * otherwise return <code>null</code>
     */
    ContextRole getContextRole(String roleCode);

    /**
     * List all applications installed in current client and current context instance
     *
     * @param list List to hold {@link ContextApplicationInstallation}, cannot be <ode>null</ode>
     */
    void listApplicationInstallations(List<ContextApplicationInstallation> list);

    /**
     * Get an application installed in current client and current context instance
     *
     * @param applicationId Application Id, cannot be <code>null</code>
     * @return An application installation or <code>null</code> if not found
     */
    ContextApplicationInstallation getApplicationInstallation(String applicationId);

    /**
     * Check if an application was installed and enabled in current client and
     * current context instance.
     *
     * @param application Application
     * @return true if the application is supported
     */
    boolean supportApplication(Application application);

    /**
     * List public resources in current client and current context instance
     * which resources all users can access.
     *
     * @param list List to hold public authorities, cannot be <code>null</code>
     */
    void listPublicResources(List<ClientResource> list);

    /**
     * List public service methods which are associated with public resources
     * in current client and current context instance
     *
     * @param list List to hold pubic service methods, cannot be <code>null</code>
     */
    void listPublicMethods(List<ServiceMethod> list);

    /**
     * Check if the user's request is permitted in current client and current context instance
     * according to user's role and public authorities
     *
     * @param httpMethod Http method, cannot be blank
     * @param URI Request URI, cannot be blank
     * @return true if the request passes access checking
     */
    boolean checkServiceMethodAccess(String httpMethod, String URI);

    /**
     * Default entry application code in current client and current context instance
     * @return Application code, it must not be blank
     */
    String getEntryApplicationCode();

    /**
     * Default entry nav item in current client and current context instance
     * @return Navigation item's node Id, it must not be blank
     */
    String getEntryNavItem();

    /**
     * Default entry language in current client and current context instance
     *
     * @return Language, it must not be <code>null</code>
     */
    Locale getEntryLanguage();

}
