# Traceability Microservice

Audit and traceability service for the Food Court system. It stores order timelines and provides efficiency metrics for operational visibility.

## System Context
- Part of the Food Court microservices suite.
- Receives traceability events from the Order service.
- Consults the User service for access control and user context when needed.

## Responsibilities
- Persist order trace events and status history.
- Provide order and employee efficiency metrics.
- Enforce secure access to audit data.

## Architecture (Hexagonal / Ports and Adapters)
This service follows Hexagonal Architecture with explicit ports and adapters.
- `domain/`: core models, business rules, ports, and use cases.
- `application/`: DTOs, mappers, and handlers that orchestrate use cases.
- `infrastructure/`: REST controllers, Mongo adapters, HTTP clients, security configuration, and JWT implementation.

## Tech Stack
- Spring Boot 4, Spring Security, JWT
- Spring Data MongoDB
- MapStruct, Springdoc OpenAPI

## Data Store
- MongoDB (local default: `mongodb://localhost:27018/traceability_db`)

## API
- Base URL: `http://localhost:8093/traceability-api`
- Swagger UI: `http://localhost:8093/traceability-api/swagger-ui.html`

## Local Development
1. Start the database: `docker compose up -d` (uses `docker-compose.yml`)
2. Run the service: `./gradlew bootRun`
3. Run tests: `./gradlew test`

## System Diagrams
![Project Architecture](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/project-architecture.png)
![SQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/sql-entities-diagram.png)
![Order Flow Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/order-flow-diagram.png)
![NoSQL Entities Diagram](https://raw.githubusercontent.com/diegnghtmr/food-court-ui/main/docs/nosql-entities-diagram.png)

## Related Repositories
- [user-microservice](https://github.com/diegnghtmr/user-microservice)
- [food-court-microservice](https://github.com/diegnghtmr/food-court-microservice)
- [order-microservice](https://github.com/diegnghtmr/order-microservice)
- [traceability-microservice](https://github.com/diegnghtmr/traceability-microservice)
- [food-court-ui](https://github.com/diegnghtmr/food-court-ui)
