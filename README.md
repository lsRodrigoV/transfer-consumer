Bank Transfer Processing with Kafka & Spring Boot
This project demonstrates an asynchronous bank transfer processing system built with Spring Boot and Apache Kafka, following clean architecture principles, clear separation of concerns, and concepts inspired by Domain-Driven Design (DDD).
The goal is to showcase how real-world financial events can be modeled, produced, and consumed in a decoupled and scalable architecture.

Architecture Overview
The system is divided into two main responsibilities:
Producer (Transfer Service)
 Responsible for initiating a transfer and publishing a domain event to Kafka.


Consumer (Processing Service)
 Responsible for consuming the event, executing the business logic, and transforming the data into domain models.


High-level flow
REST Controller
Application Use Case
Kafka Producer
Kafka Topic
Kafka Consumer
Application Use Case
Domain Model

This approach ensures low coupling, high cohesion, and clear ownership of responsibilities.

Project Structure
src/main/java
├── application
│   └── usecase
│       └── ProcessTransferUseCase.java
│
├── domain
│   ├── event
│   │   └── TransferCreatedEvent.java
│   └── model
│       └── Transferencia.java
│
├── infrastructure
│   └── kafka
│       ├── TransferKafkaProducer.java
│       └── TransferKafkaConsumer.java
│
├── config
│   └── kafka
│       └── KafkaConfig.java
│
└── controller
    └── TransferController.java

 Architectural Principles Applied
Application Use Cases
Represent business intentions, not technical actions
Centralize business rules
Orchestrate domain behavior without infrastructure coupling

Messaging with Apache Kafka
Asynchronous communication
Fault tolerance and scalability
Loose coupling between producer and consumer services

How to Run the Application
Prerequisites
Java 17+
Apache Kafka
Docker (optional, for local Kafka setup)
Running Kafka with Docker
docker-compose up -d
Starting the Application
./mvnw spring-boot:run

Example Workflow
A transfer request is received via REST API
The Controller delegates to an Application Use Case
A TransferCreatedEvent is published to Kafka
The Consumer listens to the topic and receives the event
The Use Case processes the transfer
Data is mapped into a domain model for further handling



 Future Improvements
Database persistence
Account balance validation
Dead Letter Queue (DLQ) handling
Observability (logging, metrics, tracing)
Automated unit and integration tests
Idempotency and retry strategies



This project was developed for professional growth and hands-on practice with:
Java
Spring Boot
Apache Kafka
Clean Architecture
Domain-Driven Design
Event-driven systems


LINK PRODUCER: https://github.com/lsRodrigoV/transfer-producer/




