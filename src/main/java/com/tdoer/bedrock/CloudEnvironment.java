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

import com.tdoer.bedrock.application.Application;
import com.tdoer.bedrock.context.ContextConfig;
import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.context.ContextType;
import com.tdoer.bedrock.product.Client;
import com.tdoer.bedrock.product.ClientConfig;
import com.tdoer.bedrock.product.Product;
import com.tdoer.bedrock.tenant.ProductRental;
import com.tdoer.bedrock.tenant.Tenant;
import com.tdoer.bedrock.tenant.TenantClient;
import com.tdoer.springboot.util.LocaleUtil;
import org.springframework.util.Assert;

import java.util.Locale;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class CloudEnvironment {

    private Tenant tenant;

    private ProductRental productRental;

    private TenantClient tenantClient;

    private ContextInstance contextInstance;

    private Application application;

    private Locale language;

    private EnvironmentDigest digest;

    public CloudEnvironment(ProductRental productRental, TenantClient tenantClient, ContextInstance contextInstance, Application application, Locale language) {
        Assert.notNull(productRental, "ProductRental cannot be null");
        Assert.notNull(tenantClient, "TenantClient cannot be null");
        Assert.notNull(contextInstance, "ContextInstance cannot be null");
        Assert.notNull(application, "Application cannot be null");
        Assert.notNull(language, "Language cannot be null");

        this.tenant = productRental.getTenant();
        this.productRental = productRental;
        this.tenantClient = tenantClient;
        this.contextInstance = contextInstance;
        this.application = application;
        this.language = language;

        // Generate digest right now, in order to avoid infinite loop to load
        // product, client objects in wrong use case.
        //
        digest = generateDigest();
    }

    protected EnvironmentDigest generateDigest(){
        EnvironmentDigest digest = new EnvironmentDigest();
        digest.setTenantId(tenant.getId());
        digest.setProductId(productRental.getProductId());
        digest.setClientId(tenantClient.getClientId());
        digest.setContextPath(contextInstance.getContextPath().getAbsoluteValue());
        digest.setApplicationId(application.getId());
        digest.setLanguage(LocaleUtil.getLocale(language));
        return digest;
    }
    /**
     * Current tenant is being requesting
     *
     * @return Current tenant
     */
    public Tenant getTenant() {
        return tenant;
    }

    public Client getClient() {
        return tenantClient.getClient();
    }

    public Product getProduct() {
        return productRental.getProduct();
    }

    public ContextInstance getContextInstance() {
        return contextInstance;
    }

    public Application getApplication() {
        return application;
    }

    public Locale getLanguage() {
        return language;
    }

    public EnvironmentDigest getDigest() {
        return digest;
    }

    public ProductRental getProductRental() {
        return productRental;
    }

    public TenantClient getTenantClient() {
        return tenantClient;
    }

    // -----------------------------------------------------------
    // Shortcut methods
    // -----------------------------------------------------------

    public ClientConfig getClientConfig() {
        return tenantClient.getClient().getClientConfig();
    }

    public ContextConfig getContextConfig() {
        return contextInstance.getContextConfig();
    }

    public ContextPath getContextPath() {
        return contextInstance.getContextPath();
    }

    public ContextType getRootContextType(){
        return tenant.getContextType();
    }

    public Long getTenantId() {
        return tenant.getId();
    }

    public Long getClientId() {
        return tenantClient.getClientId();
    }

    public Long getProductId() {
        return productRental.getProductId();
    }

    public Long getApplicationId() {
        return getApplication().getId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("CloudEnvironment{");
        sb.append("Tenant[").append(tenant.getId()).append(",").append(tenant.getCode()).append(",").append(tenant.getName()).append("],");
        sb.append("TenantClient[").append(getClient().getCode()).append(",").append(getClient().getName()).append("],");
        sb.append("Product[").append(getProduct().getCode()).append(",").append(getProduct().getName()).append("],");
        sb.append("ContextInstance[").append(contextInstance.getContextPath().getAbsoluteValue()).append(",").append(contextInstance.getName()).append("],");
        sb.append("Application[").append(application.getCode()).append(",").append(application.getName()).append("],");
        sb.append("Language[").append(LocaleUtil.getLocale(language)).append("]");
        sb.append("}");
        return sb.toString();
    }
}
