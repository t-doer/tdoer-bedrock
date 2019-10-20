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

import com.tdoer.bedrock.product.Product;

import java.io.Serializable;
import java.util.Date;
import java.util.Locale;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ProductRental extends Serializable {
    /**
     * Tenant who rents the product
     * @return Tenant, must not be <code>null</code>
     */
    Tenant getTenant();

    /**
     * Product which is rented by tenant
     * @return Product, must not be <code>null</code>
     */
    Product getProduct();

    /**
     * Tenant Id
     * @return Tenant Id, must not be <code>null</code>
     */
    Long getTenantId();

    /**
     * Product Id
     * @return Product Id, must not be <code>null</code>
     */
    Long getProductId();

    /**
     * Default language the tenant uses
     * @return Language, must not be <code>null</code>
     */
    Locale getDefaultLanguage();

    /**
     * Rental start date
     * @return Date, must not be <code>null</code>
     */
    Date getStartDate();

    /**
     * Rental end date
     * @return Date, must not be <code>null</code>
     */
    Date getEndDate();

    /**
     * Check if the rental is still active, not due
     * @return true if still active
     */
    boolean isActive();

}
