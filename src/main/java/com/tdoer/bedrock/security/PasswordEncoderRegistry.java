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
package com.tdoer.bedrock.security;

import com.tdoer.bedrock.product.ClientCategory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.util.HashMap;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class PasswordEncoderRegistry {
    protected HashMap<ClientCategory, PasswordEncoder> mapper;

    protected PasswordEncoder defaultPasswordEncoder;

    public PasswordEncoderRegistry(PasswordEncoder defaultPasswordEncoder) {
        Assert.notNull(defaultPasswordEncoder, "Default PasswordEncoder cannot be null");

        mapper = new HashMap<>();
        this.defaultPasswordEncoder = defaultPasswordEncoder;
    }

    public PasswordEncoder getPasswordEncoder(ClientCategory clientCategory) {
        PasswordEncoder ret = mapper.get(clientCategory);
        if (ret == null) {
            ret = defaultPasswordEncoder;
        }

        return ret;
    }

    public void register(ClientCategory clientCategory, PasswordEncoder passwordEncoder) {
        Assert.notNull(clientCategory, "ClientCategory can't not be null");
        Assert.notNull(passwordEncoder, "PasswordEncoder can't be null");

        mapper.put(clientCategory, passwordEncoder);
    }
}
