# Fundoo Notes Backend

## Project Overview

Fundoo Notes is a backend application developed using Spring Boot. It is designed to manage user accounts and notes with a scalable, layered architecture. The project is built incrementally using use cases (UCs), progressing from basic backend development to advanced distributed system concepts.

The application demonstrates real-world backend practices including REST API design, database integration, validation, exception handling, caching, asynchronous messaging, and cross-cutting concerns.

---

## Architecture

The project follows a layered architecture:

Controller → Service → DTO → Entity → Repository → Database

### Layers

- **Controller Layer**  
  Handles HTTP requests and responses.

- **Service Layer**  
  Contains business logic and application flow.

- **DTO Layer**  
  Transfers data between client and server with validation.

- **Entity Layer**  
  Represents database tables using JPA.

- **Repository Layer**  
  Handles database operations using Spring Data JPA.

- **Exception Layer**  
  Provides centralized error handling.

---

## Technologies Used

- Java 21/25
- Spring Boot 4
- Spring Data JPA
- MySQL
- Redis (Caching)
- RabbitMQ (Messaging)
- Spring AOP
- Spring Batch
- Maven
- Git & GitHub

---

## Part 1 — Backend Fundamentals

### Objective

To build a strong backend foundation using Spring Boot with proper layering, REST APIs, and database integration.

---

### Implemented Use Cases

#### UC1 — Project Setup and Configuration
- Spring Boot project initialization
- application.properties configuration

#### UC2 — Entity and Repository Layer
- Created User and Note entities
- Implemented JPA repositories

#### UC3 — DTO Layer
- Request and response DTOs
- Separation of API and database models

#### UC4 — Service Layer
- Business logic for user and note operations

#### UC5 — Controller Layer
- REST APIs for user and note management

#### UC6 — Exception Handling
- Global exception handler
- Custom exceptions (UserNotFound, InvalidCredentials, etc.)

#### UC7 — Authentication Flow
- Basic login system
- Token generation utility

---

### Features Achieved

- Layered architecture
- RESTful API design
- Database integration (MySQL)
- DTO validation
- Global exception handling
- Clean request-response flow

---

## Part 2 — Advanced Backend Development

### Objective

To enhance the backend with scalable and production-level features such as caching, messaging, AOP, and batch processing.

---

### Implemented Use Cases

#### UC8 — Dependencies and Configuration
- Added Redis, RabbitMQ, AOP, and Batch dependencies
- Configured application properties

#### UC9 — Redis Caching
- Implemented token caching using Redis
- Improved performance for authentication

#### UC10 — RabbitMQ Messaging
- Configured exchanges, queues, and bindings
- Implemented producer and consumer

#### UC11 — Service Integration
- Integrated Redis and RabbitMQ into service layer
- Async event triggered on user registration

#### UC12 — AOP Logging
- Implemented aspect-based logging
- Logging applied to service layer methods

#### UC13 — Spring Batch Setup
- Basic batch configuration for future bulk operations

#### UC14 — Application Refactor
- Corrected main application class naming and structure

---

### Features Achieved

- Asynchronous processing using RabbitMQ
- Distributed caching using Redis
- AOP-based logging
- Scalable backend design principles
- Integration of multiple backend technologies

---

## API Flow (Example)

User Registration Flow:

1. Request hits Controller  
2. DTO validation occurs  
3. Service processes business logic  
4. Data saved via Repository  
5. RabbitMQ event triggered  
6. Response returned to client  

---

## How to Run the Project

### Prerequisites

- Java installed
- MySQL running
- Redis running (WSL or local)
- RabbitMQ running

---

### Steps

1. Clone the repository  
2. Configure database in application.properties  
3. Start Redis server  
4. Start RabbitMQ server  
5. Run the application:

```bash
mvn spring-boot:run
