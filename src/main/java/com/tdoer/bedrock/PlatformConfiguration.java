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
import com.tdoer.bedrock.service.ServiceRepository;
import com.tdoer.bedrock.tenant.RentalCenter;
import com.tdoer.bedrock.web.CloudEnvironmentParseFilterFactory;
import com.tdoer.bedrock.web.RequestCloudEnvironmentExtractor;
import com.tdoer.springboot.util.StatusCodeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */

@Configuration
public class PlatformConfiguration implements BeanPostProcessor, ApplicationContextAware {
    /* -------------------------------------------------
     * The beans below should be implemented and provided by
     * Bedrock Implementation
     */
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Autowired
    private RootContextType rootContextType;

    @Autowired
    private ContextPathParser contextPathParser;

    @Autowired
    private ContextConfigCenter contextConfigCenter;

    @Autowired
    private ContextInstanceCenter contextInstanceCenter;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ClientConfigCenter clientConfigCenter;

    @Autowired
    private RentalCenter rentalCenter;

    @Value("${spring.application.name}")
    private String serviceCode;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof PlatformConfiguration){
            Platform.setCloudConfiguration((PlatformConfiguration)bean);

            // Register status codes
            StatusCodeUtil.registerStatusCodes(BedrockErrorCodes.class);
        }
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }

    public String getServiceCode(){
        return serviceCode;
    }

    public RootContextType rootContextType(){
        return rootContextType;
    }

    public ContextPathParser contextPathParser(){
        return contextPathParser;
    }

    public ContextConfigCenter contextConfigCenter(){
        return contextConfigCenter;
    }

    public ContextInstanceCenter contextInstanceCenter(){
        return contextInstanceCenter;
    }

    public ServiceRepository serviceRepository(){
        return serviceRepository;
    }

    public ApplicationRepository applicationRepository(){
        return applicationRepository;
    }

    public ProductRepository productRepository(){
        return productRepository;
    }

    public ClientConfigCenter clientConfigCenter(){
        return clientConfigCenter;
    }

    public RentalCenter rentalCenter(){
        return rentalCenter;
    }

}
