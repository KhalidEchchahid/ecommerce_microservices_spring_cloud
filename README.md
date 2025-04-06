# Ecommerce Microservices Platform

A robust, scalable ecommerce platform built with a microservices architecture using Spring Boot and Spring Cloud. This project demonstrates modern cloud-native application development practices with emphasis on service isolation, resilience, and scalability.

**Academic Project:** This is an academic project developed at FSTM Mohammadia under the guidance of Professor Omar El Beggar.

**Team:**

- Khalid Echchahid
- Ayman Amkassou

## Architecture Overview

The application follows a microservices architecture pattern with the following components:

```
                           ┌──────────────┐
                           │              │
                           │   Keycloak   │
                           │              │
                           └───────┬──────┘
                                   │
                                   ▼
┌──────────────┐          ┌──────────────┐
│              │          │              │
│ Config Server├─────────►│ API Gateway  │◄───── Client Applications
│              │          │              │
└──────┬───────┘          └───────┬──────┘
       │                          │
       │                          │
       ▼                          ▼
┌──────────────┐          ┌──────────────┐
│              │          │              │
│  Discovery   │◄─────────┤ Microservices│
│   Service    │          │              │
└──────────────┘          └───────┬──────┘
                                  │
                          ┌───────┴──────────────────────┐
                          │                              │
             ┌────────────┼──────────────┬───────────────┼───────────┐
             │            │              │               │           │
     ┌───────▼───┐  ┌─────▼────┐  ┌──────▼─────┐  ┌──────▼─────┐  ┌─▼────────────┐
     │           │  │          │  │            │  │            │  │              │
     │  Product  │  │ Customer │  │   Order    │  │  Payment   │  │ Notification │
     │  Service  │  │ Service  │  │  Service   │  │  Service   │  │   Service    │
     │           │  │          │  │            │  │            │  │              │
     └───────────┘  └──────────┘  └────────────┘  └────────────┘  └──────────────┘
          │              │              │                │                │
          ▼              ▼              ▼                ▼                ▼
     ┌─────────┐    ┌─────────┐    ┌─────────┐     ┌─────────┐      ┌─────────┐
     │PostgreSQL│    │PostgreSQL│    │PostgreSQL│     │ MongoDB │      │  Kafka  │
     └─────────┘    └─────────┘    └─────────┘     └─────────┘      └─────────┘
```

## Core Services

### Infrastructure Services

1. **Config Server**

   - Centralizes configuration for all microservices
   - Uses Spring Cloud Config Server with native profile
   - Stores service-specific configuration files

2. **Discovery Service**

   - Service registry and discovery using Netflix Eureka
   - Enables dynamic service location without hardcoded URLs
   - Provides health monitoring and load balancing support

3. **API Gateway**
   - Single entry point for client requests
   - Routes requests to appropriate services
   - Handles cross-cutting concerns like authentication and monitoring

### Business Services

4. **Product Service**

   - Manages product catalog and inventory
   - CRUD operations for products and categories
   - PostgreSQL database for product data

5. **Customer Service**

   - Handles customer registration and profiles
   - Manages customer addresses and account information
   - PostgreSQL database for customer data

6. **Order Service**

   - Processes order creation and management
   - Orchestrates interactions between other services
   - Uses Kafka for event-driven notifications
   - PostgreSQL database for order data

7. **Payment Service**

   - Handles payment processing and transactions
   - Supports multiple payment methods
   - MongoDB for flexible payment data storage

8. **Notification Service**
   - Event-driven service for sending notifications
   - Consumes Kafka events from other services
   - Handles email notifications for various system events

## Technology Stack

- **Spring Boot**: Core framework for building standalone applications
- **Spring Cloud**:
  - Netflix Eureka: Service discovery
  - Spring Cloud Config: Centralized configuration
  - Spring Cloud Gateway: API Gateway
  - OpenFeign: Declarative REST clients
  - Spring Cloud Sleuth: Distributed tracing
- **Databases**:
  - PostgreSQL: Relational database for most services
  - MongoDB: NoSQL database for Payment Service
- **Messaging**:
  - Apache Kafka: Asynchronous event-driven communication
- **Security**:
  - Keycloak: Identity and access management
- **Containerization**:
  - Docker: Application containerization
  - Docker Compose: Multi-container orchestration

## Getting Started

### Prerequisites

- JDK 17+
- Maven 3.8+
- Docker and Docker Compose
- Git

### Setup and Running

1. **Clone the repository**

```bash
git clone https://github.com/khalidEchchahid/ecommerce_microservices_spring_cloud.git
cd ecommerce_microservices_spring_cloud
```

2. **Build all services**

```bash
mvn clean package -DskipTests
```

3. **Start the infrastructure services with Docker Compose**

```bash
docker-compose up -d
```

4. **Start the services in the following order:**

   a. Config Server
   b. Discovery Service
   c. Remaining business services
   d. API Gateway

Each service can be started with:

```bash
cd services/service-name
mvn spring-boot:run
```

Alternatively, use the Docker Compose file to start all services:

```bash
docker-compose up -d
```

5. **Access the application**

- Eureka Dashboard: http://localhost:8761
- API Gateway: http://localhost:8222
- Keycloak Admin Console: http://localhost:8080/admin (admin/admin)
- Zipkin: http://localhost:9411

## API Documentation

The following APIs are available through the API Gateway:

### Product Service

- `GET /api/v1/products` - List all products
- `GET /api/v1/products/{id}` - Get product by ID
- `POST /api/v1/products` - Create a new product
- `PUT /api/v1/products/{id}` - Update a product
- `DELETE /api/v1/products/{id}` - Delete a product

### Customer Service

- `GET /api/v1/customers` - List all customers
- `GET /api/v1/customers/{id}` - Get customer by ID
- `POST /api/v1/customers` - Register a new customer
- `PUT /api/v1/customers/{id}` - Update customer information

### Order Service

- `GET /api/v1/orders` - List all orders
- `GET /api/v1/orders/{id}` - Get order by ID
- `POST /api/v1/orders` - Create a new order

### Payment Service

- `POST /api/v1/payments` - Process a payment
- `GET /api/v1/payments/{id}` - Get payment details

## Communication Patterns

The services communicate using both:

1. **Synchronous communication** via REST APIs using OpenFeign
2. **Asynchronous communication** via Kafka for event-driven processes

## Security

Security is implemented at multiple levels:

1. **API Gateway** - Authentication and authorization via Keycloak
2. **SSL/TLS** - Secure communication between services
3. **Service-level security** - Method-level security with Spring Security

## Monitoring and Observability

- **Distributed Tracing** with Zipkin
- **Logging** with structured logging format
- **Metrics** collection for monitoring

## Development

### Adding a New Service

1. Create a new Spring Boot application
2. Add dependencies for Spring Cloud
3. Configure the application to register with Eureka
4. Add the Config Client for centralized configuration
5. Implement the required business logic
6. Build and deploy the service

### Service Communication

- Use OpenFeign for synchronous service-to-service communication
- Use Kafka for asynchronous event-driven communication


## Acknowledgments

- Professor Omar El Beggar for guidance and supervision of this academic project
- Ayman Amkassou for his equal contribution as co-developer of this project from the ground up
- Spring Cloud documentation and tutorials
- Microservices patterns and best practices

