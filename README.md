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

### Supplier Entity

**Description:** Represents a supplier providing products to the system.

| Attribute      | Type        | Description                                      |
|----------------|-------------|--------------------------------------------------|
| `id`           | Long        | Unique identifier for the supplier               |
| `name`         | String      | Name of the supplier.                            |
| `logoUrl`      | String      | URL for the supplier's logo.                     |
| `email`        | String      | Email address of the supplier.                   |
| `phone`        | String      | Contact phone number of the supplier.            |
| `address`      | String      | Physical address of the supplier.                |
| `createdAt`    | Timestamp   | Creation timestamp                               |
| `updatedAt`    | Timestamp   | Last update timestamp                            |

**Relationships:**
- **One-to-Many:** A supplier can have multiple products (`List<Product>`).

---

### Product Entity

**Description:** Represents a product that can be purchased or sold in the system.

| Attribute         | Type        | Description                                                         |
|--------------------|-------------|--------------------------------------------------------------------|
| `id`              | Long        | Unique identifier for the product                                   |
| `name`            | String      | Name of the product.                                                |
| `mainImage`       | String      | URL for the main image of the product.                              |
| `description`     | String      | Detailed description of the product.                                |
| `price`           | Double      | Price of the product.                                               |
| `initialQuantity` | Integer     | Initial stock quantity available for the product.                   |
| `quantity`        | Integer     | Current available stock quantity.                                   |
| `supplier`        | Supplier    | Supplier providing the product (Foreign Key: `supplier_id`).        |
| `createdAt`       | Timestamp   | Creation timestamp.                                                 |
| `updatedAt`       | Timestamp   | Last update timestamp.                                              |

**Relationships:**
- **Many-to-One:** Each product is linked to one supplier (`Supplier`).
- **One-to-Many:** Each product can have multiple images (`List<Image>`).

**Notes:**
- Supports cascading operations for related images. 

---

### Customer

**Description:** Represents a customer who can place orders.

| Attribute      | Type          | Description                                          |
|----------------|---------------|------------------------------------------------------|
| `id`           | Long          | Unique identifier for the customer                   |
| `firstName`    | String        | Customer's first name                                |
| `lastName`     | String        | Customer's last name                                 |
| `email`        | String        | Customer's email address                             |
| `phone`        | String        | Customer's phone number                              |
| `address`      | String (Text) | Customer's address                                   |
| `orders`       | List<Order>   | List of orders placed by the customer                |
| `createdAt`    | Timestamp     | Record creation timestamp                            |
| `updatedAt`    | Timestamp     | Last update timestamp                                |

---

### Order

**Description:** Represents a customer's order containing multiple items.

| Attribute      | Type                | Description                                           |
|----------------|---------------------|-------------------------------------------------------|
| `id`           | Long                | Unique identifier for the order                       |
| `orderStatus`  | OrderStatus (Enum)  | Status of the order (IN_PROGRESS, CANCELED, COMPLETED)|
| `totalAmount`  | Double              | Total amount for the order                            |
| `customer`     | Customer            | Associated customer                                   |
| `orderItems`   | List<OrderItem>     | List of items in the order                            |
| `createdAt`    | Timestamp           | Record creation timestamp                             |
| `updatedAt`    | Timestamp           | Last update timestamp                                 |

#### **Enum Notes:**

- **OrderStatus**: Tracks the status of the order.

---

### OrderItem

**Description:** Represents an item in an order.

| Attribute      | Type        | Description                                            |
|----------------|-------------|--------------------------------------------------------|
| `id`           | Long        | Unique identifier for the order item                   |
| `quantity`     | Integer     | Quantity of the product                                |
| `order`        | Order       | Associated order                                       |
| `product`      | Product     | Associated product                                     |
| `createdAt`    | Timestamp   | Record creation timestamp                              |
| `updatedAt`    | Timestamp   | Last update timestamp                                  |

---

### Purchase

**Description:** Represents a purchase transaction involving a supplier.

| Attribute          | Type                 | Description                                        |
|---------------------|----------------------|---------------------------------------------------|
| `id`               | Long                 | Unique identifier for the purchase                 |
| `totalAmount`      | Double               | Total amount of the purchase                       |
| `supplier`         | Supplier             | Associated supplier                                |
| `purchaseItems`    | List<PurchaseItem>   | List of items included in the purchase             |
| `createdAt`        | Timestamp            | Record creation timestamp                          |
| `updatedAt`        | Timestamp            | Last update timestamp                              |

---

### PurchaseItem

**Description:** Represents an item in a purchase.

| Attribute        | Type        | Description                                             |
|------------------|-------------|---------------------------------------------------------|
| `id`             | Long        | Unique identifier for the purchase item                 |
| `quantity`       | Integer     | Quantity of the product purchased                       |
| `subTotalPrice`  | Double      | Subtotal price for this purchase item                   |
| `purchase`       | Purchase    | Associated purchase                                     |
| `product`        | Product     | Associated product                                      |
| `createdAt`      | Timestamp   | Record creation timestamp                               |
| `updatedAt`      | Timestamp   | Last update timestamp                                   |

---

### User

**Description:** Represents a system user with roles and permissions.

| Attribute          | Type              | Description                                        |
|---------------------|-------------------|---------------------------------------------------|
| `id`               | Long              | Unique identifier for the user                     |
| `firstName`        | String            | User's first name                                  |
| `lastName`         | String            | User's last name (inherited from Person)           |
| `email`            | String            | User's email address (inherited from Person)       |
| `password`         | String            | Encrypted password                                 |
| `photoUrl`         | String            | Optional URL to the user's profile photo           |
| `active`           | Boolean           | Indicates if the user account is active            |
| `roles`            | Set<Role>         | Roles assigned to the user                         |
| `notifications`    | List<Notification>| Notifications related to the user                  |
| `createdAt`        | Timestamp         | Record creation timestamp                          |
| `updatedAt`        | Timestamp         | Last update timestamp                              |

---

### Role

**Description:** Represents a role assigned to users.

| Attribute       | Type              | Description                                         |
|------------------|-------------------|----------------------------------------------------|
| `id`            | Long              | Unique identifier for the role                      |
| `name`          | String            | Name of the role                                    |
| `privileges`    | Set<Privilege>    | Privileges associated with the role                 |
| `createdAt`     | Timestamp         | Record creation timestamp                           |
| `updatedAt`     | Timestamp         | Last update timestamp                               |

#### **Enum Notes:**

- **EPrivilege**: Defines granular permissions like USER_READ, ROLE_WRITE, etc.

---

### Notification

**Description:** Represents system notifications.

| Attribute            | Type                | Description                                         |
|----------------------|---------------------|-----------------------------------------------------|
| `id`                 | Long                | Unique identifier for the notification              |
| `isSeen`             | Boolean             | Indicates if the notification has been seen         |
| `notificationType`   | NotificationType    | Type of notification                                |
| `body`               | String (Text)       | Notification body text                              |
| `user`               | User                | Associated user (optional)                          |
| `createdAt`          | Timestamp           | Record creation timestamp                           |
| `updatedAt`          | Timestamp           | Last update timestamp                               |

#### **Enum Notes:**

- **NotificationType**: Tracks notification types like RECEIVED_CONTACT_MESSAGE.

--- 





















## 
















