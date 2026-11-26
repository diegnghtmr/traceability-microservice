# Food Court Traceability API reference

## API endpoints

Base URL: `http://localhost:8093/traceability-api`. Use the running service’s [Swagger UI](http://localhost:8093/traceability-api/swagger-ui.html) for request and response schemas. The routes below expect a bearer JWT.

| Method and path | Purpose |
| --- | --- |
| `POST /trace` | Record an order transition, including the timestamp and state change |
| `GET /trace/client/{clientId}` | Return transitions for the authenticated customer’s own ID |
| `GET /trace/efficiency/orders` | Report the elapsed minutes from pending to delivered for the authenticated owner’s restaurant |
| `GET /trace/efficiency/employees` | Report completed-order counts, average fulfillment time, and efficiency grouped by employee for the owner’s restaurant |

Reports require the owner role, use the caller’s restaurant ID, and count an order only when both start and delivery timestamps exist. Employee reports group by the employee recorded at the preparation transition. The application handler optionally resolves employee names over HTTP by calling the Identity email lookup. These reports describe the traces received; when Order cannot send a transition, current order state is unchanged and the history may be incomplete.

## Data store and environment

MongoDB stores timestamped transition records in `traceability_db`. The [Compose file](../docker-compose.yml) publishes the database at host port `27018`, leaving port `27017` available for the Order service’s separate store.

| Name | Purpose | Default with a host-run API |
| --- | --- | --- |
| `SPRING_MONGODB_URI` | MongoDB connection URI | `mongodb://localhost:27018/traceability_db` |
| `SPRING_MONGODB_DATABASE` | Transition database name | `traceability_db` |
| `JWT_SECRET` | Verifies Food Court bearer JWTs | Local development value; verification must match Identity’s signing configuration |
| `USER_SERVICE_URL` | Identity base URL used for employee names | `http://localhost:8090/user-api` |

`docker compose up -d traceability-mongodb` starts only MongoDB. `docker compose up -d` starts this API and its database. It does not start Order or Identity.

## Ports and adapters

REST controllers and application handlers adapt the incoming requests to the domain. Framework-free use cases query the trace store and apply customer and restaurant scope rules; infrastructure implements MongoDB, HTTP, and the caller context.

| Domain output port | Adapter | Responsibility |
| --- | --- | --- |
| `ITracePersistencePort` | `TraceMongoAdapter` | Store and query transition documents |
| `IUserServicePort` | `UserHttpAdapter` | Resolve employee names from the Identity API |
| `IUserContextPort` | `UserContextAdapter` | Resolve the authenticated caller and restaurant |

Order sends transition records; the web app reads customer history and owner reports. Traceability does not write the current order state.

## System diagrams

- [System context](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/project-architecture.png) — UI, services, and data stores.
- [SQL entities](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/sql-entities-diagram.png) — historical relational design; current orders are not stored in PostgreSQL.
- [Document entities](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/nosql-entities-diagram.png) — the order and trace document model.
- [Order lifecycle](https://github.com/diegnghtmr/food-court-ui/blob/main/docs/order-flow-diagram.png) — status transitions through pickup or cancellation.
