/*
 * Copyright 2019 T-Doer (tdoer.com).
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
package com.tdoer.bedrock;

import com.tdoer.bedrock.application.ApplicationRepository;
import com.tdoer.bedrock.context.ContextCenter;
import com.tdoer.bedrock.context.ContextPathParser;
import com.tdoer.bedrock.product.ProductRepository;
import com.tdoer.bedrock.security.AuthenticationUtil;
import com.tdoer.bedrock.service.Service;
import com.tdoer.bedrock.service.ServiceRepository;
import com.tdoer.bedrock.tenant.RentalCenter;
import com.tdoer.bedrock.tenant.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-17
 */
public class Platform {
    private static PlatformConfiguration configuration;

    private static Logger logger = LoggerFactory.getLogger(Platform.class);

    /**
     * Set configuration into the platform. Platform's cloud configuration can
     * only be set once right after {@link PlatformConfiguration} is initialized
     * which will be auto loaded by Spring Boot framework.
     *
     * @param platformConfiguration Platform configuration, cannot be <code>null</code>
     */
    static void setPlatformConfiguration(PlatformConfiguration platformConfiguration){
        Assert.notNull(platformConfiguration, "Platform configuration cannot be null");
        Assert.isTrue(configuration == null, "Platform configuration already existed, cannot be set any more");
        configuration = platformConfiguration;
        logger.info("Platform configuration is set");
    }

    // ------------------------------------------------
    // From configuration
    // ------------------------------------------------
    public static ContextPathParser getContextPathParser(){
        return configuration.getContextPathParser();
    }

    public static ContextCenter getContextCenter(){
        return configuration.getContextCenter();
    }

    public static ServiceRepository getServiceRepository(){
        return configuration.getServiceRepository();
    }

    public static ApplicationRepository getApplicationRepository(){
        return configuration.getApplicationRepository();
    }

    public static ProductRepository getProductRepository(){
        return configuration.getProductRepository();
    }

    public static RentalCenter getRentalCenter(){
        return configuration.getRentalCenter();
    }

    public static Service getCurrentService(){
        return getServiceRepository().getService(configuration.getServiceCode());
    }

    // -----------------------------------------------
    // From parser filter
    // -----------------------------------------------

    public static CloudEnvironment getCurrentEnvironment(){
        return CloudEnvironmentHolder.getEnvironment();
    }

    public static User getCurrentUser(){
        return AuthenticationUtil.getUser();
    }

}
