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

import com.tdoer.springboot.util.LocaleUtil;
import org.springframework.util.StringUtils;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class EnvironmentDigest {

    protected Long tenantId;

    protected String productId;

    protected String clientId;

    protected String contextPath;

    protected String applicationId;

    protected String language;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public static EnvironmentDigest fromDigestString(String digestStr) {
        String[] arr = StringUtils.delimitedListToStringArray(digestStr, "|");
        int i = 0;
        EnvironmentDigest digest = new EnvironmentDigest();
        digest.setTenantId(Long.parseLong(arr[i++]));
        digest.setProductId(arr[i++]);
        digest.setClientId(arr[i++]);
        digest.setContextPath(arr[i++]);
        digest.setApplicationId(arr[i++]);
        digest.setLanguage(arr[i++]);
        return digest;
    }

    public String toDigestString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tenantId).append("|");
        sb.append(productId).append("|");
        sb.append(clientId).append("|");
        sb.append(contextPath).append("|");
        sb.append(applicationId).append("|");
        sb.append(language);
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EnvironmentDigest[");
        sb.append(tenantId).append(",");
        sb.append(productId).append(",");
        sb.append(clientId).append(",");
        sb.append(contextPath).append(",");
        sb.append(applicationId).append(",");
        sb.append(language);
        sb.append("]");
        return sb.toString();
    }
}
