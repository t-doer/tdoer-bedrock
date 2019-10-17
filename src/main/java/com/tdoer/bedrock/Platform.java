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
import com.tdoer.bedrock.context.ContextConfigCenter;
import com.tdoer.bedrock.context.ContextInstanceCenter;
import com.tdoer.bedrock.context.ContextPathParser;
import com.tdoer.bedrock.context.RootContextType;
import com.tdoer.bedrock.product.ClientConfigCenter;
import com.tdoer.bedrock.product.ProductRepository;
import com.tdoer.bedrock.security.AuthenticationUtil;
import com.tdoer.bedrock.service.Service;
import com.tdoer.bedrock.service.ServiceRepository;
import com.tdoer.bedrock.tenant.RentalCenter;
import com.tdoer.bedrock.tenant.User;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-17
 */
public class Platform {
    private static CloudConfiguration configuration;

    static void setCloudConfiguration(CloudConfiguration cloudConfiguration){
        configuration = cloudConfiguration;
    }

    // ------------------------------------------------
    // From configuration
    // ------------------------------------------------
    public static RootContextType getRootContextType(){
        return configuration.rootContextType();
    }

    public static ContextPathParser getContextPathParser(){
        return configuration.contextPathParser();
    }

    public static ContextConfigCenter getContextConfigCenter(){
        return configuration.contextConfigCenter();
    }

    public static ContextInstanceCenter getContextInstanceCenter(){
        return configuration.contextInstanceCenter();
    }

    public static ServiceRepository getServiceRepository(){
        return configuration.serviceRepository();
    }

    public static ApplicationRepository getApplicationRepository(){
        return configuration.applicationRepository();
    }

    public static ProductRepository getProductRepository(){
        return configuration.productRepository();
    }

    public static ClientConfigCenter getClientConfigCenter(){
        return configuration.clientConfigCenter();
    }

    public static RentalCenter getRentalCenter(){
        return configuration.rentalCenter();
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
