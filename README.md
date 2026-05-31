# Stock Trading Order Engine

A high-performance **Enterprise Stock Trading Order Engine Backend** built using **Java 21**, **Spring Boot**, and **MySQL**.

This system simulates a real-world trading backend capable of handling order placement, order execution, order validation, order book management, and secure transaction processing.

---

# Table of Contents

1. Project Overview
2. Features
3. Architecture
4. Technology Stack
5. Project Structure
6. Installation & Setup
7. Database Configuration
8. Application Configuration
9. Running the Application
10. API Documentation
11. Authentication
12. Postman Testing
13. Logging
14. Performance Configuration
15. Monitoring
16. Error Handling
17. Future Enhancements
18. Contributing
19. License

---

# Project Overview

The Stock Trading Order Engine is designed to process trading orders efficiently.

Core Responsibilities:

- Accept buy/sell orders
- Validate orders
- Maintain order books
- Match orders
- Execute trades
- Persist transaction records
- Provide REST APIs
- Support monitoring and logging

---

# Features

## Order Management
- Create orders
- Cancel orders
- Retrieve order status

## Trading Engine
- Order matching
- Price-based execution
- FIFO processing

## Validation
- Request validation
- Business rule validation

## Security
- Spring Security integration
- Protected endpoints

## Monitoring
- Spring Actuator support

## Persistence
- MySQL integration
- JPA/Hibernate

## API Documentation
- Swagger / OpenAPI

## Logging
- File-based logging

---

# Technology Stack

### Backend
- Java 21
- Spring Boot 3.3.5

### Frameworks
- Spring Web
- Spring Data JPA
- Spring Security
- Spring Validation
- Spring AOP
- Spring Actuator
- Spring Cache

### Database
- MySQL

### Documentation
- Swagger / OpenAPI

### Build Tool
- Maven

---

# Project Structure

```plaintext
src
├── main
│   ├── java
│   │   └── com.tradingengine
│   │       ├── controller
│   │       ├── service
│   │       ├── repository
│   │       ├── entity
│   │       ├── dto
│   │       ├── exception
│   │       ├── config
│   │       ├── engine
│   │       └── util
│   │
│   └── resources
│       └── application.properties
│
└── test
```

---

# Installation & Setup

## Prerequisites

Install:

- Java 21
- Maven
- MySQL

Verify:

```bash
java -version
mvn -version
mysql --version
```

---

# Clone Repository

```bash
git clone <your-repository-url>
cd Stocktradingorderengine
```

---

# Database Configuration

Create database:

```sql
CREATE DATABASE trading_engine_db;
```

Update:

```properties
spring.datasource.username=root
spring.datasource.password=root
```

---

# Application Configuration

Main configurations:

```properties
server.port=8080

spring.datasource.url=
spring.jpa.hibernate.ddl-auto=update

spring.jpa.show-sql=true
```

Swagger:

```properties
springdoc.swagger-ui.enabled=true
```

---

# Running the Application

Build:

```bash
mvn clean install
```

Run:

```bash
mvn spring-boot:run

```

Application URL:

```plaintext
http://localhost:8080
```

---

# API Documentation

Swagger UI:

```plaintext
http://localhost:8080/swagger-ui.html
```

API Docs:

```plaintext
http://localhost:8080/v3/api-docs
```

---

# Authentication

Security is enabled using:

```plaintext
Spring Security
```

Protected endpoints require authentication.

Default behavior:

```plaintext
401 Unauthorized
403 Forbidden
```

---

# Postman Testing

Example request:

### Place Order

POST

```plaintext
/api/orders
```

Body:

```json
{
  "symbol":"AAPL",
  "quantity":10,
  "price":150,
  "side":"BUY"
}
```

Response:

```json
{
  "success":true,
  "message":"Order placed successfully"
}
```

---

### Get Orders

GET

```plaintext
/api/orders
```

---

### Cancel Order

DELETE

```plaintext
/api/orders/{id}
```

---

# Logging

Logs are stored at:

```plaintext
logs/trading-engine.log
```

Enable debug:

```properties
logging.level.com.tradingengine=DEBUG
```

---

# Performance Configuration

Connection Pool:

```properties
maximum-pool-size=20
minimum-idle=5
```

Hibernate Optimization:

```properties
batch_size=50
order_inserts=true
order_updates=true
```

---

# Monitoring

Actuator Endpoint:

```plaintext
/actuator
```

Health:

```plaintext
/actuator/health
```

Metrics:

```plaintext
/actuator/metrics
```

---

# Error Handling

Common Errors:

### 400 Bad Request
Invalid input.

### 401 Unauthorized
Authentication missing.

### 403 Forbidden
Access denied.

### 500 Internal Server Error
Unexpected server issue.

---

# Validation

Examples:

- Quantity > 0
- Price > 0
- Mandatory fields
- Enum validation

---

5. Open Pull Request

---

