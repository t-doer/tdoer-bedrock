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

import com.tdoer.bedrock.Platform;
import com.tdoer.bedrock.context.ContextConfig;
import com.tdoer.bedrock.context.ContextInstance;
import com.tdoer.bedrock.context.ContextPath;
import com.tdoer.bedrock.context.ContextType;

/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public class BaseUser implements User {
    protected Long id;

    protected String guid;

    protected Long tenantId;

    protected String account;

    protected String password;

    protected String name;

    protected String phone;

    protected String email;

    protected AccountStatus status;

    protected CredentialStatus credentialStatus;

    protected String category;

    protected boolean real;

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    @Override
    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    @Override
    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public AccountStatus getStatus() {
        return status;
    }

    public void setStatus(AccountStatus status) {
        this.status = status;
    }

    @Override
    public CredentialStatus getCredentialStatus() {
        return credentialStatus;
    }

    public void setCredentialStatus(CredentialStatus credentialStatus) {
        this.credentialStatus = credentialStatus;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean isReal() {
        return real;
    }

    public void setReal(boolean real) {
        this.real = real;
    }

    /**
     * Instance code, unique in a tenant
     *
     * @return Instance code, must not be blank
     */
    @Override
    public String getCode() {
        return getGuid();
    }

    /**
     * Parent context instance, that's, the tenant to which the user belongs
     *
     * @return the tenant to which the user belongs, must not be <code>null</code>
     */
    @Override
    public ContextInstance getParent() {
        return Platform.getContextCenter().getContextInstance(getTenantId(), ContextType.TENANT.getType(),
                getTenantId());
    }

    /**
     * The top parent of the instance, that's, the tenant to which the user belongs
     *
     * @return the tenant to which the user belongs, must not be <code>null</code>
     */
    @Override
    public ContextInstance getTopParent() {
        return getParent();
    }

    /**
     * Context path to the context instance, say, '1.1-2.1', it's always
     * globally unique.
     *
     * @return Context path, must not be <code>null</code>
     */
    @Override
    public ContextPath getContextPath() {
        return new ContextPath(ContextType.USER.getType(), getId(), new ContextPath(ContextType.TENANT.getType(),
                getTenantId()));
    }

    /**
     * Context type of the context instance. An instance must below to only one
     * context type.
     *
     * @return Context type, must not be <code>null</code>
     */
    @Override
    public ContextType getContextType() {
        return ContextType.USER;
    }

    /**
     * The instance's configurations, for example, available applications, context roles etc.
     *
     * @return Context configuration, must not be <code>null</code>
     */
    @Override
    public ContextConfig getContextConfig() {
        return Platform.getContextCenter().getContextConfig(this);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("BaseUser[")
                .append("id='").append(id).append("' ")
                .append("tenantId='").append(tenantId).append("' ")
                .append("account='").append(account).append("' ")
                .append("password='").append(password).append("' ")
                .append("name='").append(name).append("' ")
                .append("phone='").append(phone).append("' ")
                .append("email='").append(email).append("' ")
                .append("status='").append(status).append("' ")
                .append("credentialStatus='").append(credentialStatus).append("' ")
                .append("category='").append(category).append("' ")
                .append("real='").append(real).append("' ")
                .append("]");
        return sb.toString();
    }
}
