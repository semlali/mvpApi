# 📦 Order Management System (MVP)

## 🚀 Project Overview

This is a Spring Boot application for an Order Management System, providing customers with the ability to:

* View the history of their orders.
* Submit a refund request for a specific product.

## ✅ Features

* Order Management: View customer orders.
* Refund Management: Request refunds for specific products.
* Exception Handling: Global exception handler for clean API responses.
* DTOs: Clean separation between entities and API responses.
* Clean Architecture: Controllers, Services, Repositories, and Mappers.

## 📂 Project Structure

```
src/main/java/com/example/ordermanagement
├── controller       --> REST Controllers (API Endpoints)
├── service          --> Business Logic Services Interfaces
├── service/impl     --> Service Implementations
├── repository       --> JPA Repositories (Data Access Layer)
├── model            --> Entities (JPA Models)
├── dto              --> Data Transfer Objects (DTOs)
├── mapper           --> DTO <-> Entity Mappers
└── exception        --> Custom Exceptions and Error Handling

src/test/java/com/example/ordermanagement
├── controller       --> Controller Unit Tests
└── service          --> Service Unit Tests
```

## ⚙️ Technologies

* Java 21
* Spring Boot
* Spring Data JPA (Hibernate)
* MockMvc for Controller Testing
* JUnit & Mockito for Unit Testing


##  📖 API Documentation
  ToDo with openapi

## 🔧 Configuration
The application uses the following configurations in application.properties:


### Database:
* spring.datasource.url=jdbc:postgresql://localhost:5432/mvpbd
* spring.datasource.username=
* spring.datasource.password=

### JWT Secret:  
* jwt.secret=<your_secret_key>


### Spring Security:
* spring.security.oauth2.resourceserver.jwt.issuer-uri=http://localhost:8080

## 🚀 Getting Started

### Prerequisites

* Java 21
* Maven

### Setup & Run

1. Clone this repository:

   ```bash
   git clone  https://github.com/semlali/mvpApi.git
   cd mvpApi
   ```

2. Build the project:

   ```bash
   mvn clean install
   ```

3. Run the application:

   ```bash
   mvn spring-boot:run
   ```

4. Access the API:

    * Orders: `GET /api/orders/{customerId}` (with path variable : customerId)
    * Refund: `POST /api/refund` (with body : orderItemId)

### Testing

* Run all tests:

  ```bash
  mvn test
  ```

## 📌 Future Improvements

* Add Swagger/OpenAPI documentation.
* Adapt TU to handle authentication and authorization.
* Implement pagination for customer orders.
* Add integration tests (Controller -> Service -> Repository).
