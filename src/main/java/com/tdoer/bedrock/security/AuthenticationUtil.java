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
package com.tdoer.bedrock.security;

import com.tdoer.bedrock.BedrockErrorCodes;
import com.tdoer.bedrock.tenant.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class AuthenticationUtil {
    static Logger logger = LoggerFactory.getLogger(AuthenticationUtil.class);

    /**
     * Get current session's UserDetails from security context, if a user is not authenticated, return null
     *
     * @return UserDetails or null
     */
    public static UserDetails getUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedUserException(BedrockErrorCodes.UNAUTHORIZED);
        }

        return ((UserDetails) authentication.getPrincipal());
    }

    /**
     * Get current session's User from security context, if a a user is not authenticated, return null
     *
     * @return User or null
     */
    public static User getUser() {
        UserDetails ud = null;
        try {
            ud = getUserDetails();
        } catch (UnauthorizedUserException uue) {
            // ignored
        }

        if (ud != null) {
            return ud.getUser();
        }

        return null;
    }
}
