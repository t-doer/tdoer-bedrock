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
package com.tdoer.bedrock.tenant;

import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextPath;

import java.io.Serializable;
import java.util.List;

/**
 * @Description A tenant is a customer who rents products to provider its massive users. In a SaaS product,
 * user is always a tenant's user and tenant pays for the SaaS services. Each tenant has a
 * unique identifier and its product data is isolated logically or physically by the unique identifier.
 * <p>
 * In the design, tenant is also the top context instance {@link com.tdoer.bedrock.context.ContextInstance}
 * of all context pathes {@link ContextPath}.
 * </p>
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface Tenant extends ContextInstance, Serializable {
    @Override
    default boolean isTenant(){
        return true;
    }

    @Override
    default ContextInstance getParent(){
        return null;
    }

    /**
     * List the tenant's product rentals
     * @param list List to hold product rentals, cannot be <code>null</code>
     */
    void listProductRentals(List<ProductRental> list);

    /**
     * List the tenant's clients
     *
     * @param list List to hold tenant clients, cannot be <code>null</code>
     */
    void listTenantClients(List<TenantClient> list);
}
