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

import com.tdoer.bedrock.application.ApplicationInstallation;

import java.util.List;
import java.util.Locale;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextConfig {

    /**
     * List current user's roles in the context instance.
     *
     * @param list
     */
    void listCurentUserRoles(List<ContextRole> list);

    /**
     * List context roles of given user Id
     *
     * @param userId
     * @param list
     */
    void listUserRoles(Long userId, List<ContextRole> list);


    /**
     * List available roles in current context instance
     *
     * @param list
     */
    void listContextRoles(List<ContextRole> list);

    /**
     * Get context role available in the context instance
     *
     * @param roleId
     * @return
     */
    ContextRole getContextRole(Long roleId);

    /**
     * List public authorities in current context instance which all users can access.
     *
     * @param list
     */
    void listPublicAuthorities(List<PublicAuthority> list);


    /**
     * Check if the http request path's access is permitted in current context instance.
     *
     * @param httpMethod
     * @param path
     * @return
     */
    boolean checkServiceMethodAccess(String httpMethod, String path);

    /**
     * List all applications installed in current contextPath instance
     *
     * @param list
     */
    void listApplicationInstallations(List<ApplicationInstallation> list);

    /**
     * Get an application installed in current contextPath instance.
     *
     * @param applicationId
     * @return An application installation or null if not found
     */
    ContextApplicationInstallation getApplicationInstallation(String applicationId);

    /**
     * Check whether an application was installed and enabled in current context instance.
     *
     * @param applicationId Application Id
     * @return true if the application is supported
     */
    boolean supportApplication(String applicationId);

    String getEntryApplicationId();

    String getEntryNavItem();

    Locale getEntryLanguage();

}
