# Stock Management System

## Tech Stack

- Backend: Spring Boot 3
- Database: SQL Server
- Build Tool: Maven
- API Documentation: Swagger OpenAPI
- Java Version: 17+

---

## Common Commands

### Run Backend
cd backend
./mvnw spring-boot:run

### Build Project
cd backend
./mvnw clean install

### Run Tests
cd backend
./mvnw test

---

## Architecture

The backend follows layered architecture:

- controller -> REST APIs
- service -> business logic
- repository -> database access
- entity -> JPA entities
- dto -> request/response objects
- mapper -> entity/dto transformations

---

## Rules

- Never expose entities directly in APIs
- Always use DTOs
- Business logic belongs in services
- Controllers should stay thin
- Use validation annotations on DTOs
- Use constructor injection only
- Follow REST naming conventions

---

## API Standards

- Base path: /api
- Use plural resource names
- Return DTO responses
- Validate all request bodies

---

## Database

- SQL Server
- Hibernate ddl-auto=update
- Use JPA repositories

---

## Documentation

Swagger UI:
http://localhost:8080/swagger-ui/index.html