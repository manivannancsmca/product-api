# Product Management API

A Spring Boot application for managing products with MySQL database and Swagger documentation.

## Features

- **Java 21** with **Spring Boot 3.x**
- **MySQL** database integration (with H2 option for development)
- **Swagger/OpenAPI** documentation
- **Request validation** using Jakarta Validation
- **MVC design pattern** implementation
- **RESTful API** with proper HTTP status codes
- **Global exception handling**
- **CRUD operations** for products
- **Search and filter** capabilities

## Tech Stack

- Java 21
- Spring Boot 3.2.0
- Spring Data JPA
- MySQL Connector
- H2 Database (for development/testing)
- SpringDoc OpenAPI (Swagger)
- Lombok
- Maven

## Project Structure

```
src/main/java/com/example/productmanagement/
├── ProductManagementApplication.java    # Main application entry point
├── config/
│   └── SwaggerConfig.java               # Swagger/OpenAPI configuration
├── controller/
│   └── ProductController.java           # REST API endpoints
├── dto/
│   ├── ProductRequest.java              # Request DTO with validation
│   └── ProductResponse.java             # Response DTO
├── entity/
│   └── Product.java                     # JPA Entity
├── exception/
│   ├── ResourceNotFoundException.java    # Custom exception
│   ├── DuplicateResourceException.java   # Custom exception
│   └── GlobalExceptionHandler.java       # Global exception handling
├── repository/
│   └── ProductRepository.java           # Data access layer
└── service/
    └── ProductService.java              # Business logic
```

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/products` | Create a new product |
| GET | `/api/products` | Get all products |
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products/sku/{sku}` | Get product by SKU |
| GET | `/api/products/search?name={name}` | Search products by name |
| GET | `/api/products/filter?active={status}` | Filter products by active status |
| PUT | `/api/products/{id}` | Update a product |
| DELETE | `/api/products/{id}` | Delete a product |

## Getting Started

### Prerequisites

- Java 21 or higher
- Maven 3.6+
- MySQL 8.0+ (or use H2 for development)

### Database Setup

1. Create a MySQL database:
```sql
CREATE DATABASE product_db;
```

2. Update database credentials in `src/main/resources/application.properties`:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

**Note:** For development/testing, you can use H2 database by uncommenting the H2 configuration in `application.properties` and commenting out the MySQL configuration.

### Build and Run

1. Clone the repository
2. Navigate to the project directory
3. Build the project:
```bash
mvn clean install
```

4. Run the application:
```bash
mvn spring-boot:run
```

5. Access the application:
   - **Swagger UI:** http://localhost:8080/swagger-ui.html
   - **API Docs:** http://localhost:8080/api-docs

## API Documentation

The API is fully documented using Swagger/OpenAPI. Once the application is running, you can access the interactive documentation at:

- **Swagger UI:** http://localhost:8080/swagger-ui.html

## Request/Response Examples

### Create Product

**Request:**
```json
{
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "quantity": 50,
  "sku": "LAPTOP-001",
  "active": true
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Laptop",
  "description": "High-performance laptop",
  "price": 999.99,
  "quantity": 50,
  "sku": "LAPTOP-001",
  "active": true,
  "createdAt": "2024-01-15T10:30:00",
  "updatedAt": "2024-01-15T10:30:00"
}
```

### Validation Error Response

```json
{
  "timestamp": "2024-01-15T10:30:00",
  "status": 400,
  "error": "Validation Error",
  "message": "Validation failed for input data",
  "errors": {
    "name": "Product name is required",
    "price": "Price must be greater than 0"
  }
}
```

## Validation Rules

| Field | Rules |
|-------|-------|
| name | Required, 2-100 characters |
| description | Optional, max 500 characters |
| price | Required, must be greater than 0 |
| quantity | Required, must be non-negative |
| sku | Required, alphanumeric and hyphens only |
| active | Required, boolean |

## License

This project is licensed under the Apache License 2.0.