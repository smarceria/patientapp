# Patient Management App

This Spring Boot application provides a service for managing patient data.

## Features

Create, read, update, and delete (CRUD) operations for patients.

## Dependencies

- Spring Boot Starter Data JPA
- Spring Boot Starter Web
- H2 Database (in-memory, with initial data stored in resources/data.sql)
- Spring Boot Starter Tomcat (optional, if deploying to a container)
- ModelMapper (for object mapping)
- JUnit 5 (for testing)

## Getting Started

- Clone the repository or download the project files.
- Install Java 17
- Install a Maven build tool (https://maven.apache.org/).
- Open a terminal in the project directory.
- Run ``mvn clean install`` to build the project.

## Running the Application

- Run ``mvn spring-boot:run`` to start the application using an embedded Tomcat server.
- Access the application in your browser at http://localhost:8080 (default port).

## Development

- The application uses Spring Data JPA to interact with a database.
- Unit tests are included using JUnit 5.

## Client App

The client application can be found in: https://github.com/smarceria/patientapp-client
