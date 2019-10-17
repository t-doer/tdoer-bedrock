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
     * Client-side error codes: 4001 ~ 4200
     */
    @ReasonPhrase("No tenant product found by (tenantId, clientId) - ({0}, {1})")
    int NO_TENANT_CLIENT_BY_IDS = 4001;

    @ReasonPhrase("No tenant product found from request's server name: {0}")
    int NO_TENANT_CLIENT_BY_HOST = 4002;

    @ReasonPhrase("No product rental found (tenantId, productId) - ({0}, {1})")
    int NO_PRODUCT_RENTAL = 4003;

    @ReasonPhrase("Product rental is expired (startDate, endDate) - ({0}, {1})")
    int EXPIRED_PRODUCT_RENTAL = 4004;

    @ReasonPhrase("No context instance found for the context path: {0}")
    int NO_CONTEXT_INSTANCE = 4005;

    @ReasonPhrase("Illegal context path request: {0}")
    int ILLEGAL_CONTEXT_PATH = 4006;

    @ReasonPhrase("No application Id found in request: {0}")
    int NO_APPLICATION_ID_IN_REQUEST = 4007;

    @ReasonPhrase("Unknown application Id: {0}")
    int UNKNOWN_APPLICATION_ID = 4008;

    @ReasonPhrase("Invalid environment digest: {0}")
    int INVALID_ENV_DIGEST = 4009;

    @ReasonPhrase("Context instance ({0}) is not supported by the tenant product ({1}, {2})")
    int CONTEXT_INSTANCE_NOT_SUPPORTED_BY_TENANT_CLIENT = 4010;

    @ReasonPhrase("Application ({0}) is not supported by the tenant product ({1}, {2})")
    int APPLICATION_NOT_SUPPORTED_BY_TENANT_CLIENT = 4011;

    @ReasonPhrase("Application ({0}) is not supported by the context instance ({1})")
    int APPLICATION_NOT_SUPPORTED_BY_CONTEXT_INSTANCE = 4012;

    @ReasonPhrase("No client found (clientId) - ({0})")
    int NO_CLIENT = 4013;

    @ReasonPhrase("No tenant found (tenantId) - ({0})")
    int NO_TENANT = 4014;

    /* ----------------------------------------------------
     * Server-side error codes: 5001 ~ 5200
     * ----------------------------------------------------
     */


}
