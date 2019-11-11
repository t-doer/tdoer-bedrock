# T-Doer Bedrock SaaS Framework Specification

The project is an implementation of **T-Doer Bedrock SaaS Framework Specification**. It provides 
main data models and interfaces, web filters and API entrance "com.tdoer.bedrock.Platform". The 
specification needs its implementation to provide the implementation of below interfaces:

- com.tdoer.bedrock.application.ApplicationRepository
- com.tdoer.bedrock.context.ContextCenter
- com.tdoer.bedrock.context.ContextPathParser
- com.tdoer.bedrock.product.ProductRepository
- com.tdoer.bedrock.service.ServiceRepository
- com.tdoer.bedrock.tenant.RentalCenter

T-Doer provides the specification's default implementation, please see
[T-Doer Bedrock SaaS Framework Implementation](https://github.com/t-doer/tdoer-bedrock-impl).

## Filters

- `com.tdoer.bedrock.web.CloudEnvironmentProcessingFilter`, the filter should be installed before to call 
  `Platform#getCurrentEnvironment()` to get current environment.
- `com.tdoer.bedrock.web.CloudServiceCheckAccessFilter`, the filter should be installed to check the access
  to service after `com.tdoer.bedrock.web.CloudEnvironmentProcessingFilter`

## Core Concepts

Please see T-Doer documents.

