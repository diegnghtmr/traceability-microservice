# Traceability Microservice

![Java](https://img.shields.io/badge/Java-25-blue)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0-brightgreen)
![Architecture](https://img.shields.io/badge/Architecture-Hexagonal-black)
![Database](https://img.shields.io/badge/DB-MongoDB-green)

Audit and traceability service for the Food Court platform. It records order events and produces operational efficiency metrics.

## Table of Contents
- [Overview](#overview)
- [Responsibilities](#responsibilities)
- [System Context](#system-context)
- [Architecture](#architecture)
- [Ports and Adapters](#ports-and-adapters)
- [Data Store](#data-store)
- [API](#api)
- [Local Development](#local-development)
- [System Diagrams](#system-diagrams)
- [Related Repositories](#related-repositories)

## Overview
Traceability Microservice stores audit events for every order lifecycle change and exposes metrics for employee and order performance.

## Responsibilities
- Persist trace events and status history.
- Provide efficiency metrics for orders and employees.
- Enforce secure access to audit data.

## System Context
Inbound adapters:
- REST API (Spring MVC) under `/traceability-api`.

Outbound adapters:
- MongoDB persistence.
- User service client for access validation and context.

Consumers:
- Order service and Food Court UI.

## Architecture
Hexagonal Architecture keeps business rules independent from frameworks.

- Domain: models, use cases, and port interfaces.
- Application: DTOs, mappers, and handlers that orchestrate use cases.
- Infrastructure: REST controllers, Mongo adapters, HTTP clients, security, and configuration.

Dependency rule: infrastructure depends on application and domain; domain has no framework dependencies.

## Ports and Adapters
- `ITracePersistencePort` -> `TraceMongoAdapter`.
- `IUserServicePort` -> `UserHttpAdapter`.
- `IUserContextPort` -> `UserContextAdapter`.

## Data Store
- MongoDB: `mongodb://localhost:27018/traceability_db`

## API
- Base URL: `http://localhost:8093/traceability-api`
- Swagger UI: `http://localhost:8093/traceability-api/swagger-ui.html`

## Local Development
1. Start database: `docker compose up -d`
2. Run service: `./gradlew bootRun`
3. Run tests: `./gradlew test`

## System Diagrams
![Project Architecture](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/project-architecture.png)
_High-level system context showing actors, UI, services, and databases._
![SQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/sql-entities-diagram.png)
_Relational model for users, restaurants, dishes, and orders in PostgreSQL._
![NoSQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/nosql-entities-diagram.png)
_Document model for traceability events and order history in MongoDB._
![Order Flow Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/order-flow-diagram.png)
_Order lifecycle from pending to delivered or canceled, including state transitions._

## Related Repositories
- [user-microservice](https://github.com/diegnghtmr/user-microservice)
- [food-court-microservice](https://github.com/diegnghtmr/food-court-microservice)
- [order-microservice](https://github.com/diegnghtmr/order-microservice)
- [traceability-microservice](https://github.com/diegnghtmr/traceability-microservice)
- [food-court-ui](https://github.com/diegnghtmr/food-court-ui)

## License

MIT, see [LICENSE](LICENSE).
