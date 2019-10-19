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
package com.tdoer.bedrock.product;

import com.tdoer.bedrock.BedrockErrorCodes;
import com.tdoer.springboot.error.ErrorCodeException;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class ClientNotFoundException extends ErrorCodeException {

    protected Object identifier;

    public ClientNotFoundException(Long clientId) {
        super(BedrockErrorCodes.CLIENT_NOT_FOUND_BY_ID, new Long[]{clientId});
        this.identifier = clientId;
    }

    public ClientNotFoundException(String clientCode) {
        super(BedrockErrorCodes.CLIENT_NOT_FOUND_BY_CODE, new String[]{clientCode});
        this.identifier = clientCode;
    }

    public Object getIdentifier() {
        return identifier;
    }
}
