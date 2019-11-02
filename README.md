# T-Doer Bedrock SaaS Framework Specification

The project is an implementation of **T-Doer Bedrock SaaS Framework Specification**. It provides 
main data models and interfaces, web filters and API entrance "com.tdoer.bedrock.Platform". The 
specification needs its implementation to provider the service providers below:

- com.tdoer.bedrock.application.ApplicationRepository
- com.tdoer.bedrock.context.ContextCenter
- com.tdoer.bedrock.context.ContextPathParser
- com.tdoer.bedrock.product.ProductRepository
- com.tdoer.bedrock.service.ServiceRepository
- com.tdoer.bedrock.tenant.RentalCenter

T-Doer provides the specification's default implementation, 
[T-Doer Bedrock SaaS Framework Implementation](https://github.com/t-doer/tdoer-bedrock-impl).

## Core Concepts

Please see T-Doer documents.

## Todo

- CacheManager messages
- ExtensionDomains, set status according to init values
