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
package com.tdoer.bedrock.context;
/**
 * @Description
 * @author Htinker Hu (htinker@163.com)
 * @create 2017-09-19
 */
public interface ContextPathParser {
    /**
     * Parse context path string, say "1.1-2.1-3.1" into {@link ContextPath}
     * @param contextPath Context path string, must not be <code>null</code>
     * @return {@link ContextPath} if the context path string is of the format
     * @throws InvalidContextPathException if the context path string is invalid
     */
    ContextPath parse(String contextPath) throws InvalidContextPathException;
}
