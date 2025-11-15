# Real-Time Shipment Tracker

A demo project showcasing an event-driven, microservice-based system using Java (Spring Boot), Apache Kafka, and Docker.

This project simulates a real-time supply chain tracking system. It consists of two independent microservices that communicate asynchronously using a Kafka message queue. A producer service exposes a REST API to receive shipment location updates, and a consumer service listens for these updates in real-time to process them.

## Technology Stack

* **Java 17**

* **Spring Boot:** Used for building both microservices (Spring Web, Spring for Kafka)

* **Apache Kafka:** As the real-time, fault-tolerant message broker

* **Docker & Docker Compose:** For containerizing and orchestrating all services (Kafka, Zookeeper, and the two applications)

* **Maven:** For project build and dependency management

## Architecture (How it Works)

The system is composed of four main services managed by Docker Compose: zookeeper, kafka, producer-service, and consumer-service.

The data flows as follows:

```
[User/Postman] --(1. HTTP POST)--> [Producer Service (Spring Boot)] --(2. Publish)--> [Kafka Topic: "shipment-updates"] --(3. Consume)--> [Consumer Service (Spring Boot)] --(4. Log Output)--> [Console]
```

1. A user sends an HTTP POST request with JSON data (e.g., {"shipmentId": "...", "location": "..."}) to the producer-service API endpoint.

2. The producer-service receives the request and publishes the message to a Kafka topic named shipment-updates.

3. The consumer-service, which is subscribed to this topic, instantly consumes the new message.

4. The consumer deserializes the JSON and logs the shipment details to the console, demonstrating the end-to-end, real-time data flow.


## Getting Started

Follow these instructions to build and run the entire system on your local machine.

**Prerequisites**

* Java 17 (JDK)

* Apache Maven

* Docker Desktop (must be running)

**1. Build the Applications**

Run the Maven package command in both service directories to build the executable .jar files. These .jar files are what will be copied into the Docker images.

```
# Build Producer
cd producer-service
mvn clean package -DskipTests
cd ..

# Build Consumer
cd consumer-service
mvn clean package -DskipTests
cd ..
```

**2. Build and Run the System**

With Docker Desktop running, use Docker Compose to build the custom images for your services and start all four containers.
```
# Build the Docker images
docker-compose build

# Start Kafka, Zookeeper, and both services
docker-compose up
```

<div align="center">
<strong>Note:</strong> To view the logs from all running containers, you can run: <code>docker-compose logs -f</code>
</div>

**3. Test the System**

To test the complete data pipeline, open a new terminal and send a POST request to the producer-service.

***For Windows (PowerShell):***
```
curl -Method POST -Uri http://localhost:8080/api/v1/shipments/update -Headers @{"Content-Type" = "application/json"} -Body '{"shipmentId": "TRK-123", "location": "Chicago, IL"}'
```

***For Mac/Linux (Bash):***
```
curl -X POST http://localhost:8080/api/v1/shipments/update \
-H "Content-Type" = "application/json" \
-d '{"shipmentId": "TRK-123", "location": "Chicago, IL"}'
```
You will immediately see the "RECEIVED SHIPMENT UPDATE" log appear in your docker-compose up terminal, confirming the message was processed in real-time.

**4. Shut Down the System**

When you are finished, stop and remove all the containers and networks.
```
docker-compose down
```
