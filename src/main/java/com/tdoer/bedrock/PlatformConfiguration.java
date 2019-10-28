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
import com.tdoer.bedrock.service.ServiceRepository;
import com.tdoer.bedrock.tenant.RentalCenter;
import com.tdoer.springboot.util.StatusCodeUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * Platform configuration needs Bedrock Implementation to provide below beans:
 * <p>
 *     <ol>
 *         <li>com.tdoer.bedrock.application.ApplicationRepository</li>
 *         <li>com.tdoer.bedrock.context.ContextCenter</li>
 *         <li>com.tdoer.bedrock.context.ContextPathParser</li>
 *         <li>com.tdoer.bedrock.product.ProductRepository</li>
 *         <li>com.tdoer.bedrock.service.ServiceRepository</li>
 *         <li>com.tdoer.bedrock.tenant.RentalCenter</li>
 *     </ol>
 * </p>
 *
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */

@Configuration
public class PlatformConfiguration implements BeanPostProcessor {

    /* -------------------------------------------------
     * The beans below should be implemented and provided by
     * Bedrock Implementation
     */
    private ServiceRepository serviceRepository;

    private ApplicationRepository applicationRepository;

    private ProductRepository productRepository;

    private ContextPathParser contextPathParser;

    private ContextCenter contextCenter;

    private RentalCenter rentalCenter;

    @Value("${spring.application.name}")
    private String serviceCode;

    public PlatformConfiguration(ServiceRepository serviceRepository,
                                 ApplicationRepository applicationRepository,
                                 ProductRepository productRepository,
                                 ContextPathParser contextPathParser,
                                 ContextCenter contextCenter,
                                 RentalCenter rentalCenter) {
        this.serviceRepository = serviceRepository;
        this.applicationRepository = applicationRepository;
        this.productRepository = productRepository;
        this.contextPathParser = contextPathParser;
        this.contextCenter = contextCenter;
        this.rentalCenter = rentalCenter;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof PlatformConfiguration){
            Platform.setPlatformConfiguration((PlatformConfiguration)bean);

            // Register status codes
            StatusCodeUtil.registerStatusCodes(BedrockErrorCodes.class);
        }
        return bean;
    }

    public String getServiceCode(){
        return serviceCode;
    }

    public ContextPathParser getContextPathParser(){
        return contextPathParser;
    }

    public ContextCenter getContextCenter(){
        return contextCenter;
    }

    public ServiceRepository getServiceRepository(){
        return serviceRepository;
    }

    public ApplicationRepository getApplicationRepository(){
        return applicationRepository;
    }

    public ProductRepository getProductRepository(){
        return productRepository;
    }

    public RentalCenter getRentalCenter(){
        return rentalCenter;
    }

}
