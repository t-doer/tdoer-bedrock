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

import com.tdoer.springboot.annotation.ReasonPhrase;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface BedrockErrorCodes extends com.tdoer.springboot.http.StatusCodes {

    /*
     * Client-side error codes: 40001 ~ 40100
     */
    @ReasonPhrase("Service not found by ID: {0}")
    int SERVICE_NOT_FOUND_BY_ID = 40001;

    @ReasonPhrase("Service not found by code: {0}")
    int SERVICE_NOT_FOUND_BY_CODE = 40002;

    @ReasonPhrase("Service method not found by ID: {0}")
    int SERVICE_METHOD_NOT_FOUND_BY_ID = 40003;

    @ReasonPhrase("Application not found by ID: {0}")
    int APPLICATION_NOT_FOUND_BY_ID = 40004;

    @ReasonPhrase("Application not found by code: {0}")
    int APPLICATION_NOT_FOUND_BY_CODE = 40005;

    @ReasonPhrase("Page not found by ID: {0}")
    int PAGE_NOT_FOUND_BY_ID = 40006;

    @ReasonPhrase("Page not found by (applicationId, pageCode) - ({0}, {1})")
    int PAGE_NOT_FOUND_BY_CODE = 40007;

    @ReasonPhrase("Action not found by ID: {0}")
    int ACTION_NOT_FOUND_BY_ID = 40008;

    @ReasonPhrase("Action not found by (pageId, actionCode) - ({0}, {1})")
    int ACTION_NOT_FOUND_BY_CODE = 40009;

    @ReasonPhrase("Product not found by ID: {0}")
    int PRODUCT_NOT_FOUND_BY_ID = 40010;

    @ReasonPhrase("Product not found by code: {0}")
    int PRODUCT_NOT_FOUND_BY_CODE = 40011;

    @ReasonPhrase("Client not found by ID: {0}")
    int CLIENT_NOT_FOUND_BY_ID = 40012;

    @ReasonPhrase("Client not found by code: {0}")
    int CLIENT_NOT_FOUND_BY_CODE = 40013;

    @ReasonPhrase("Tenant not found by ID: {0}")
    int TENANT_NOT_FOUND_BY_ID = 40014;

    @ReasonPhrase("Tenant not found by GUID or code: {0}")
    int TENANT_NOT_FOUND_BY_GUID_OR_CODE = 40015;

    @ReasonPhrase("Product rental not found (tenantId, productId) - ({0}, {1})")
    int PRODUCT_RENTAL_NOT_FOUND = 40016;

    @ReasonPhrase("No tenant client found by (tenantId, clientId) - ({0}, {1})")
    int NO_TENANT_CLIENT_BY_IDS = 40017;

    @ReasonPhrase("No tenant product found from request's server name: {0}")
    int NO_TENANT_CLIENT_BY_HOST = 40018;

    @ReasonPhrase("Product rental is expired (startDate, endDate) - ({0}, {1})")
    int EXPIRED_PRODUCT_RENTAL = 40019;

    @ReasonPhrase("No context instance found for the context path: {0}")
    int NO_CONTEXT_INSTANCE = 40020;

    @ReasonPhrase("Illegal context path request: {0}")
    int ILLEGAL_CONTEXT_PATH = 40021;

    @ReasonPhrase("No application Id found in request: {0}")
    int NO_APPLICATION_ID_IN_REQUEST = 40022;

    @ReasonPhrase("Unknown application Id: {0}")
    int UNKNOWN_APPLICATION_ID = 40023;

    @ReasonPhrase("Invalid environment digest: {0}")
    int INVALID_ENV_DIGEST = 40024;

    @ReasonPhrase("Context instance ({0}) is not supported by the tenant product ({1}, {2})")
    int CONTEXT_INSTANCE_NOT_SUPPORTED_BY_TENANT_CLIENT = 40025;

    @ReasonPhrase("Application ({0}) is not supported by the tenant product ({1}, {2})")
    int APPLICATION_NOT_SUPPORTED_BY_TENANT_CLIENT = 40026;

    @ReasonPhrase("Application ({0}) is not supported by the context instance ({1})")
    int APPLICATION_NOT_SUPPORTED_BY_CONTEXT_INSTANCE = 40027;

    /* ----------------------------------------------------
     * Server-side error codes: 50001 ~ 50100
     * ----------------------------------------------------
     */


}
