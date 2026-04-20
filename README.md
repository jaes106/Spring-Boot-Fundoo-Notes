# Fundoo Notes Backend — Part 1

## 1. Project Overview

Fundoo Notes is a backend application built using Spring Boot that simulates a real-world note management system.  
It allows users to register, log in, and manage personal notes.

Part 1 focuses on establishing strong backend fundamentals, including architecture, API design, persistence, and error handling.

---

## 2. Objectives of Part 1

- Build a Spring Boot application using layered architecture
- Understand request flow from controller to database
- Implement REST APIs for user and notes
- Apply DTO-based design
- Use JPA for persistence
- Implement validation and global exception handling
- Introduce basic token-based user identification

---

## 3. Architecture

The application follows a layered architecture:

Controller → Service → Repository → Database
↓
DTO Layer
↓
Exception Handling

### Layer Responsibilities

- **Controller**
  - Handles HTTP requests and responses
  - Delegates logic to service layer

- **Service**
  - Contains business logic
  - Coordinates between controller and repository

- **Repository**
  - Handles database operations using Spring Data JPA

- **DTO**
  - Controls input and output data
  - Prevents direct exposure of entity classes

- **Exception Layer**
  - Centralized error handling using `@RestControllerAdvice`

## 4. Technologies Used

- Java 17
- Spring Boot
- Spring Web
- Spring Data JPA
- MySQL
- Lombok
- Bean Validation (Jakarta Validation)

---

## 5. Configuration

Configuration is externalized using `application.properties`.

### Key Configurations

- Database connection
- JPA behavior
- Logging levels

Example:
spring.datasource.url=jdbc:mysql://localhost:3306/fundoo_notes
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


---

## 6. Features Implemented

### 6.1 User Management

- User Registration
- User Login

Flow:
1. User sends request
2. DTO validates input
3. Service processes logic
4. Repository persists data
5. Response returned

---

### 6.2 Notes Management

- Create Note
- Get All Notes

Each note is associated with a user.

---

### 6.3 Token-Based Identification (Basic)

- A simple token is generated after login
- Token is passed in request headers
- Used to identify the user

Note: This is a simplified version (not full Spring Security)

---

### 6.4 Validation

- Implemented using annotations:
  - `@NotBlank`
  - `@Email`
- Triggered using `@Valid` in controller

---

### 6.5 Global Exception Handling

Centralized error handling using:
@RestControllerAdvice

Handled exceptions:
- UserAlreadyExistsException
- UserNotFoundException
- InvalidCredentialsException
- Validation errors

---

### 6.6 Logging

Logging is configured using:
logging.level.root=INFO
logging.level.com.fundoonotes=DEBUG


Used to track:
- Application flow
- Debug information

---

## 7. API Endpoints

### User APIs

#### Register
POST /api/users/register


#### Login

POST /api/users/login

---

### Note APIs

#### Create Note

POST /api/notes

Header:

Authorization: TOKEN_<userId>

#### Get Notes

GET /api/notes

Header:

Authorization: TOKEN_<userId>

---

## 8. Key Learnings

- Importance of layered architecture
- Separation of concerns
- DTO vs Entity design
- JPA abstraction over SQL
- Centralized exception handling
- Request validation
- Basic token-based flow

---

## 9. Limitations (Part 1 Scope)

- No real authentication (JWT not implemented)
- Passwords are not encrypted
- No role-based authorization
- No advanced features (labels, reminders, etc.)
