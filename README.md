T-Doer Bedrock SaaS Framework

## Core Concepts

#### Tenant

- Tenant rents `products` to service its `users`.
- Tenant pays for SaaS products.
- Each tenant has a unique identifier.
- Product data is isolated logically or physically for each tenant.

#### Product

- Product is an `application` suit, consisting of applications.
- Product has one or more `clients` or terminals, for example, web browser, APP etc..
- Product has one or more `context instances` of `context types`, in which user plays applications.

#### Application

- Application is a relatively independent, self-contained tool, with which user can finish something.
- Application provides `pages` for clients.
- A page will have one or more `actions` so that user can manipulate.
- Both page and action associats with backend `service methods`.
- Page and action is called front-end access control item, and service method is called back-end access control item.

#### Client

- Client is a product's terminal, from which user accesses product's applications.
- A client of a product may only expose some applications of a product to users.
- A client organizes applications' pages with `navigation`, so that user can use product conveniently.

#### Context Instance

- Context instance of a context type is a background where user plays applications, say, XXX Office, YYY Club etc..
- Context type is a kind of context instances, for example, Office, Club etc..
- User owns context roles to in a context instance.
- User always play product applications in context instances.
- Each context instance has a unique `context path` to locate.
- The top context instance is always tenant.
- User's access control is based on user's context roles in context path.

#### ProductRental


#### ProductConfig


#### ContextConfig