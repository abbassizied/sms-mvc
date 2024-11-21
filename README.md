# Stock Management System

## Common Maven Commands for Spring Boot

- **Run the Application:
```bash 
$ mvn spring-boot:run
```
- **Build the Application**: If you prefer to build the application into a JAR file:
```bash
# Build the project:
$ mvn clean install
 
# Run the JAR file:
$ java -jar target/your-application-name-version.jar
# Replace your-application-name-version.jar with the actual name of the JAR file in the target folder.
```
- **Clean Build Artifacts**:
```bash
# To remove previously compiled files and prepare for a fresh build:
$ mvn clean
``` 
- **Package the Application**:
```bash
# To build the application and create a runnable JAR file:
$ mvn package
# The packaged JAR will be located in the target directory.
``` 
- **Run in a Specific Profile**:
```bash
# To run the application with a specific Spring profile:
$ mvn spring-boot:run -Dspring-boot.run.profiles=your-profile
# Replace your-profile with the desired profile (e.g., dev, prod).
``` 
- **Skip Tests During Build**:
```bash
# To skip tests during the build process:
$ mvn clean package -DskipTests 
``` 
- **Run in Debug Mode**:
```bash
# To run the application with debugging enabled:
$ mvn spring-boot:run -Dspring-boot.run.jvmArguments="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005"
# Debugging will be available on port 5005.
``` 

## **Project Metadata**

- **Project**: Maven Project (or Gradle if you prefer)
- **Language**: Java
- **Spring Boot Version**: (latest stable version, e.g., `3.x.x`)
- **Group**: `io.github.abbassizied`
- **Artifact**: `stock-management`
- **Name**: `sms-mvc (Stock Management System)`
- **Description**: `A stock management application with Spring Boot`
- **Package name**: `io.github.abbassizied.sms`
- **Packaging**: Jar
- **Java Version**: 17 (or 11, depending on compatibility needs)

## Core Entities

- **Supplier**: Represents a supplier providing products.

| Attribute      | Type        | Description                                   |
|----------------|-------------|-----------------------------------------------|
| `id`           | Integer     | Unique identifier for the supplier            |
| `companyName`  | String      | Name of the supplier                          |
| `logoUrl`      | String      | Contact person's name                         |
| `email`        | String      | Email address of the supplier                 |
| `phone`        | String      | Phone number of the supplier                  |
| `address`      | String      | Address of the supplier                       |
| `createdAt`    | Timestamp   | Timestamp when the supplier was created       |
| `updatedAt`    | Timestamp   | Timestamp when the supplier was last updated  |




























## 
















