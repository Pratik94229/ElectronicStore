# Electronic Store Backend

![GitHub last commit](https://img.shields.io/github/last-commit/Pratik94229/ElectronicStore)
![GitHub license](https://img.shields.io/github/license/Pratik94229/Ajay-Residency)

This repository contains the backend implementation of an electronic store application using Java and Spring Boot. The application is designed to manage various operations such as user management, product handling, cart management, and order processing.

## Project Structure

The project is organized into several modules and directories to handle different aspects of the application:

### 1. **config**
   - Contains configuration classes for setting up various components of the Spring Boot application, such as security, data sources, and other application-level configurations.

### 2. **controllers**
   - This package includes REST controllers that handle incoming HTTP requests, process them, and return appropriate responses. Each controller corresponds to a specific entity or feature in the application (e.g., User, Product, Cart).

### 3. **dtos**
   - The Data Transfer Objects (DTOs) are used to encapsulate the data transferred between the client and server. These are designed to minimize the amount of data sent over the network and improve security.

### 4. **entities**
   - Contains JPA entity classes that map to the database tables. These classes represent the core business objects of the application, such as `Cart`, `CartItem`, `Category`, `Product`, `Order`, and `User`.

### 5. **exceptions**
   - This package includes custom exception classes that handle various error scenarios within the application. It helps in providing meaningful error messages and handling exceptions in a unified way.

### 6. **helper**
   - The helper package contains utility classes that provide common functionalities across the application. For example, a pageable response helper class has been added to manage pagination of data in a standardized manner.

### 7. **repositories**
   - This directory contains the repository interfaces, which are responsible for data access operations. These interfaces extend Spring Data JPA repositories, providing CRUD operations and custom queries for the entities.

### 8. **services**
   - The service layer encapsulates the business logic of the application. Services are responsible for interacting with repositories and performing operations such as adding items to the cart, processing orders, and managing products.

### 9. **validators**
   - This package contains custom validation logic to ensure data integrity and enforce business rules. Validators are used to validate inputs, such as user details, product information, and other data before processing.

### 10. **User Module**
   - The user module manages all user-related operations such as registration, authentication, profile management, and more. This module interacts with the `User` entity, DTOs, services, and controllers to provide a seamless user experience.

### 11. **Cart Module**
   - This module handles all cart-related operations, including adding items to the cart, updating quantities, removing items, and viewing the cart. It uses the `Cart` and `CartItem` entities, along with corresponding services and repositories.

### 12. **Product Module**
   - The product module manages product-related functionalities such as adding new products, updating product details, deleting products, and fetching product information. It interacts with the `Product` entity and related services.

### 13. **Order Module**
   - This module is responsible for processing orders, managing order details, and handling order history. It interacts with the `Order` entity and related services to ensure smooth order processing and management.

## Features

- **User Management**: Register, login, and manage user profiles.
- **Product Management**: Add, update, delete, and fetch product details.
- **Cart Management**: Add items to the cart, update quantities, and view the cart.
- **Order Processing**: Place orders, view order history, and manage order details.
- **Image Handling**: Upload and serve user and product images.
- **Pagination**: Pageable responses for handling large data sets.

## Getting Started

### Prerequisites

- Java 17 or higher
- Maven or Gradle
- MySQL or any other supported database
- Spring Boot 3.0 or higher

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Pratik94229/electronic-store-backend.git
   ```
2. Navigate to the project directory:
   ```bash
   cd electronic-store-backend
   ```
3. Configure the `application.properties` file with your database settings.

4. Build the project:
   ```bash
   mvn clean install
   ```
5. Run the application:
   ```bash
   mvn spring-boot:run
   ```

## API Documentation

The API documentation is available via Swagger. Once the application is running, you can access the API docs at:
```
http://localhost:9090/swagger-ui/index.html
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

## Work in Progress

Currently implementing spring security.

## License

This project is licensed under the [MIT License](LICENSE).

