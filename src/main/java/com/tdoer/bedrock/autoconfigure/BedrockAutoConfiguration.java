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
package com.tdoer.bedrock.autoconfigure;

import com.tdoer.bedrock.PlatformConfiguration;
import com.tdoer.bedrock.web.CloudEnvironmentParseFilterFactory;
import com.tdoer.bedrock.web.RequestCloudEnvironmentExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Htinker Hu (htinker@163.com)
 * @create 2019-10-21
 */
@Configuration
@Import({PlatformConfiguration.class})
public class BedrockAutoConfiguration {
    @Bean
    public CloudEnvironmentParseFilterFactory cloudEnvironmentParseFilterFactory(){
        RequestCloudEnvironmentExtractor extractor = new RequestCloudEnvironmentExtractor();
        return new CloudEnvironmentParseFilterFactory(extractor);
    }
}