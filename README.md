# Banking System - Personal Banking (Microservices Architecture)

## Overview
This project is a **Personal Banking System** developed using **Java with Spring Boot** following a **microservices architecture**. It includes multiple microservices that interact through **Eureka Service Discovery** and a **Config Server** to ensure flexibility and scalability.

## Architecture
The system is designed with the following microservices:

- **Eureka Server** - Service discovery for managing microservices.
- **Config Server** - Centralized configuration management.
- **API Gateway** - Routing and security for the microservices.
- **Customer Service** - Manages customer information.
- **Product Service** - Handles banking products and accounts.
- **Transaction Service** - Manages transactions and operations.
- **Notification Service** - Sends notifications and alerts.

## Technologies Used
- **Spring Boot** - Microservices framework
- **Spring Cloud Eureka** - Service discovery
- **Spring Cloud Config** - Centralized configuration
- **Spring Cloud Gateway** - API Gateway
- **H2 Database** - In-memory database for persistence
- **JWT (JSON Web Token)** - Authentication and authorization

## Installation
To run the project locally:

1. Clone the repository:
   ```sh
   git clone https://github.com/lpsaldana/banking-system.git
   ```
2. Navigate to the project directory and start each microservice in order:
   ```sh
   mvn spring-boot:run
   ```
3. Access the Eureka Dashboard:
   ```
   http://localhost:8761
   ```
4. Make API requests through the Gateway:
   ```
   http://localhost:8080/{service-name}/endpoint
   ```

## Authentication
The system uses **JWT tokens** to authenticate users. Every request to a protected endpoint must include a valid token in the `Authorization` header.

## Future Enhancements
- Integrate a relational database such as **PostgreSQL** or **MySQL**.
- Implement **Kafka or RabbitMQ** for asynchronous messaging.
- Add **OAuth2** support for enhanced security.

---

💡 *This project is currently under development. Contributions and suggestions are welcome!*
