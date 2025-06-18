# Teleport Tracking Number Generator API

A scalable, efficient, and concurrent-safe RESTful API to generate unique tracking numbers for parcels.

---

## Features

- Built with **Java 17** and **Spring Boot 3**
- PostgreSQL persistence with JPA/Hibernate
- Unique tracking number generation with concurrency safety
- Input validation with meaningful error handling
- Correlation ID tracing in logs for request tracking
- Modular layered architecture (Controller, Service, Repository)
- Unit tests included (positive & negative cases)
- Structured and contextual logging
- Ready for horizontal scaling and deployment

---

## Prerequisites

- Java 17+
- Gradle
- PostgreSQL 12+
- (Optional) Docker & Docker Compose (if containerizing)

---

## Setup & Run
- java -jar target/tracking-number-api-1.0.0.jar

### 1. Clone the repository

## Endpoint
GET /api/v1/tracking/next-tracking-number

curl -X GET "http://localhost:8080/api/v1/tracking/next-tracking-number?origin_country_id=MY&destination_country_id=ID&weight=1.234&customer_id=de619854-b59b-425e-9db4-943979e1bd49&customer_name=RedBox%20Logistics&customer_slug=redbox-logistics" \
-H "Accept: application/json"

###DB
CREATE UNIQUE INDEX unique_tracking_number ON tracking_number(tracking_number);

```bash
git clone https://github.com/your-username/teleport-tracking-api.git
cd teleport-tracking-api


