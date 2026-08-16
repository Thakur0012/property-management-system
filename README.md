# 🏠 PropertyWale

> A cloud-ready property search and management portal built with **Java, Spring Boot, Thymeleaf, JPA/Hibernate, and relational databases**.

[![Java](https://img.shields.io/badge/Java-21-orange)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.2-brightgreen)](https://spring.io/projects/spring-boot)
[![Thymeleaf](https://img.shields.io/badge/Thymeleaf-Template%20Engine-blue)](https://www.thymeleaf.org/)
[![JPA](https://img.shields.io/badge/JPA-Hibernate-lightgrey)](https://hibernate.org/)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue)](https://www.mysql.com/)
[![Docker](https://img.shields.io/badge/Docker-Supported-2496ED)](https://www.docker.com/)

PropertyWale is a web-based **property search and management portal** inspired by popular real-estate platforms such as MagicBricks, 99acres, Housing.com, and NoBroker.

The project was developed as a **Bachelor's degree learning project** to gain practical experience in Java backend development, Spring Boot, MVC architecture, database integration, server-side rendering, Docker, and deployment concepts.

> **Project Status:** Stage 1 — Public Property Search & Details

---

## 📌 Project Overview

PropertyWale is designed to provide users with a simple platform for discovering and exploring property listings.

The current version focuses on the **public property search and property details vertical slice**, implemented end-to-end using:

```text
Entity
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
Thymeleaf View
```

The application runs immediately in development mode using an **H2 in-memory database** with automatically seeded sample data.

The project is being developed incrementally, with additional authentication, favourites, enquiry persistence, and administration features planned for future stages.

---

## ✨ Features

### 🏠 Home Page

The home page includes:

* Hero section
* Property search bar
* Property category grid
* Featured properties
* Latest property listings
* Navigation to property search
* Responsive layout

---

### 🔎 Property Search

The property search page is available at:

```text
/properties
```

Users can search and filter properties using:

* City
* Locality
* Property category
* Minimum budget
* Maximum budget
* Keyword
* Pagination

The search functionality is implemented using **server-side filtering and pagination**.

---

### 🏘️ Property Listings

Property listings display important information such as:

* Property title
* Location
* City
* Locality
* Price
* Property category
* Property status
* Property image
* Basic property information

---

### 🏡 Property Details

Each property has a dedicated details page.

The details page provides:

* Property images
* Property title
* Location
* Price
* Property type
* Key property facts
* Amenities
* Description
* Embedded Google Map
* Enquiry form UI

The enquiry form UI is currently present, while backend enquiry persistence is planned for a future stage.

---

### 🗂️ Property Categories

The application supports multiple property categories.

Sample categories are automatically loaded when the application starts.

The project currently includes **10 sample categories**.

---

### 📊 Sample Property Data

The application automatically seeds sample data during startup.

The development dataset currently contains:

* 10 property categories
* 9 sample properties
* Properties across Pune, Mumbai, and Bengaluru

This makes it possible to run and explore the application without manually entering database records.

---

### 📄 Pagination

The property search results support server-side pagination.

This allows the application to handle property listings efficiently instead of loading all records on a single page.

---

### ⚠️ Error Handling

The application includes global error handling with custom pages for:

```text
404 - Resource Not Found
500 - Internal Server Error
```

A global exception handler is also implemented in the backend.

---

## 🛠️ Technology Stack

### Backend

| Technology        | Purpose                         |
| ----------------- | ------------------------------- |
| Java 21           | Programming language            |
| Spring Boot 3.3.2 | Backend framework               |
| Spring MVC        | Web/application layer           |
| Spring Data JPA   | Database access                 |
| Hibernate         | ORM                             |
| Maven             | Build and dependency management |

### Frontend

| Technology | Purpose                     |
| ---------- | --------------------------- |
| Thymeleaf  | Server-side template engine |
| HTML5      | Page structure              |
| CSS3       | Styling                     |
| JavaScript | Client-side interactions    |

### Database

| Database   | Usage               |
| ---------- | ------------------- |
| H2         | Development         |
| MySQL      | Production / Docker |
| PostgreSQL | Production option   |

### DevOps / Deployment

| Technology        | Purpose                      |
| ----------------- | ---------------------------- |
| Docker            | Application containerization |
| Docker Compose    | Application + MySQL setup    |
| AWS EC2           | Deployment option            |
| Azure App Service | Deployment option            |
| Render            | Deployment option            |
| Railway           | Deployment option            |

---

## 🏗️ Application Architecture

PropertyWale follows a layered Spring Boot architecture.

```text
                    ┌─────────────────────┐
                    │      Browser        │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controller      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Service        │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Repository      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │      Database       │
                    └─────────────────────┘
```

The application uses:

* Controllers for handling HTTP requests
* Services for business logic
* Repositories for database operations
* Entities for database mapping
* DTOs for data transfer
* Specifications for dynamic property searching
* Thymeleaf templates for server-side rendering
* Configuration classes for application setup and sample data

---

## 📂 Project Structure

```text
propertywale/
│
├── src/
│   └── main/
│       │
│       ├── java/
│       │   └── com/
│       │       └── propertywale/
│       │           │
│       │           ├── config/
│       │           │   └── DataLoader
│       │           │
│       │           ├── controller/
│       │           │   ├── HomeController
│       │           │   └── PropertyController
│       │           │
│       │           ├── dto/
│       │           │   ├── PropertySearchCriteria
│       │           │   ├── PropertySummaryDto
│       │           │   └── PropertyDetailsDto
│       │           │
│       │           ├── entity/
│       │           │   ├── Property
│       │           │   ├── Category
│       │           │   ├── PropertyImage
│       │           │   └── PropertyStatus
│       │           │
│       │           ├── exception/
│       │           │   ├── ResourceNotFoundException
│       │           │   └── GlobalExceptionHandler
│       │           │
│       │           ├── repository/
│       │           │   ├── PropertyRepository
│       │           │   ├── CategoryRepository
│       │           │   └── specification/
│       │           │
│       │           └── service/
│       │               ├── PropertyService
│       │               ├── CategoryService
│       │               └── impl/
│       │
│       └── resources/
│           │
│           ├── templates/
│           │   ├── fragments/
│           │   │   ├── navbar.html
│           │   │   ├── footer.html
│           │   │   └── property-card.html
│           │   │
│           │   ├── error/
│           │   ├── home.html
│           │   ├── about.html
│           │   ├── contact.html
│           │   ├── property-list.html
│           │   └── property-details.html
│           │
│           ├── static/
│           │   ├── css/
│           │   ├── js/
│           │   └── images/
│           │
│           ├── application.properties
│           ├── application-dev.properties
│           └── application-prod.properties
│
├── Dockerfile
├── docker-compose.yml
├── pom.xml
└── README.md
```

---

# 🚀 Getting Started

## Prerequisites

Before running the project, make sure you have installed:

* **JDK 21**
* **Maven 3.9+**
* **Git**
* **Docker** *(optional)*

You can verify Java and Maven installations using:

```bash
java -version
```

and:

```bash
mvn -version
```

---

## 💻 Run Locally

The development configuration uses an **H2 in-memory database**, so no separate MySQL or PostgreSQL installation is required.

### 1. Clone the Repository

```bash
git clone https://github.com/YOUR-USERNAME/propertywale.git
```

### 2. Navigate to the Project

```bash
cd propertywale
```

### 3. Start the Application

```bash
mvn spring-boot:run
```

### 4. Open the Application

Open your browser and visit:

```text
http://localhost:8080
```

The application will start with sample property data automatically loaded into the H2 database.

---

# 🗄️ H2 Database Console

The development environment uses an H2 in-memory database.

The H2 console is available at:

```text
http://localhost:8080/h2-console
```

Use the following configuration:

```text
JDBC URL: jdbc:h2:mem:propertywale
Username: sa
Password:
```

The H2 database is intended for local development and learning purposes.

---

# 🐳 Run with Docker Compose

The project includes Docker support for running the Spring Boot application with MySQL.

Make sure Docker Desktop is installed and running.

Run:

```bash
docker compose up --build
```

This starts:

* Spring Boot application
* MySQL 8 database

The application will be available at:

```text
http://localhost:8080
```

To stop the containers:

```bash
docker compose down
```

---

## 🐳 Docker Architecture

```text
                 Docker Compose
                       │
          ┌────────────┴────────────┐
          │                         │
          ▼                         ▼
┌──────────────────┐      ┌──────────────────┐
│  Spring Boot App │ ───► │     MySQL 8      │
│   Port: 8080     │      │   Database       │
└──────────────────┘      └──────────────────┘
```

---

# ⚙️ Configuration

The project supports different Spring profiles.

### Development Profile

The development profile uses:

```text
H2 In-Memory Database
```

This provides a zero-setup development environment.

### Production Profile

The production profile supports:

```text
MySQL
PostgreSQL
```

Database configuration can be provided through environment variables:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
DDL_AUTO
```

Enable the production profile using:

```bash
SPRING_PROFILES_ACTIVE=prod
```

---

# ☁️ Deployment

The project is designed with cloud deployment in mind.

The production profile reads database configuration from environment variables, making it suitable for different deployment environments.

Possible deployment platforms include:

### AWS EC2

The Docker image can be deployed directly to an EC2 instance, or the application can be run using:

```bash
java -jar propertywale.jar
```

A reverse proxy such as Nginx can be placed in front of the application.

The database can be hosted using Amazon RDS.

---

### Azure App Service

The application can be deployed as a Docker container.

Database configuration can be provided through Azure App Service environment/application settings.

---

### Render / Railway

The project can be deployed using its Dockerfile.

A managed PostgreSQL database can be connected using the `DB_URL`, `DB_USERNAME`, and `DB_PASSWORD` environment variables.

---

# 📸 Screenshots

Screenshots can be added to showcase the application interface.

Recommended screenshots include:

* Home page
* Property search page
* Property details page
* Property categories
* About page
* Contact page

Example:

```markdown
## 📸 Screenshots

### Home Page

![Home Page](screenshots/home.png)

### Property Search

![Property Search](screenshots/property-search.png)

### Property Details

![Property Details](screenshots/property-details.png)
```

Create a folder such as:

```text
screenshots/
```

and place your screenshots inside it.

---

# 🔍 Search Flow

The property search functionality follows this flow:

```text
User enters search criteria
          ↓
PropertyController
          ↓
PropertySearchCriteria DTO
          ↓
PropertyService
          ↓
JPA Specification
          ↓
PropertyRepository
          ↓
Database
          ↓
Filtered & Paginated Results
          ↓
Thymeleaf Property List
```

This structure allows multiple search criteria to be combined dynamically.

---

# 📚 What I Learned

This project helped me gain practical experience with:

* Java 21
* Spring Boot
* Spring MVC
* Spring Data JPA
* Hibernate
* Thymeleaf
* MVC architecture
* Layered application architecture
* Entity relationships
* DTOs
* Repository pattern
* Service layer
* JPA Specifications
* Dynamic database searching
* Server-side pagination
* H2 database
* MySQL
* PostgreSQL
* Maven
* Docker
* Docker Compose
* Exception handling
* Error pages
* Environment-based configuration
* Basic cloud deployment concepts

---

# 🎯 Learning Objectives

The main objective of this project was to understand how a real-world Java web application can be designed and developed using Spring Boot.

Through PropertyWale, I practiced:

1. Designing a layered backend architecture
2. Connecting a Spring Boot application to a database
3. Implementing CRUD-oriented data access
4. Using JPA/Hibernate for ORM
5. Creating dynamic search functionality
6. Implementing pagination
7. Building server-rendered pages with Thymeleaf
8. Handling application exceptions
9. Managing development and production configurations
10. Containerizing an application using Docker
11. Understanding basic cloud deployment concepts

---

# 🛣️ Roadmap

The current project represents **Stage 1 — Public Property Search & Details**.

Planned future improvements include:

### 🔐 Authentication

* User registration
* User login
* Logout
* Spring Security
* Role-based access control
* `ROLE_USER`
* `ROLE_ADMIN`
* BCrypt password hashing

### ❤️ Favourites

* Save properties
* Remove saved properties
* View favourite properties

### 📩 Enquiries

* Persist property enquiries
* User enquiry history
* Admin enquiry inbox
* Enquiry status management

### 👨‍💼 Admin Dashboard

* Admin authentication
* Property CRUD
* Category CRUD
* User management
* Enquiry management
* Dashboard statistics
* Property management

### 🖼️ Property Management

* Add properties from admin dashboard
* Edit properties
* Delete properties
* Multiple image upload
* Property status management

### 📍 Maps

* Google Maps API integration
* Property location selection
* Richer map experience
* Location-based property search

### 📧 Contact

* Persist Contact Us submissions
* Admin contact inbox
* Email notifications

---

# 📈 Current Project Status

| Feature                  | Status      |
| ------------------------ | ----------- |
| Home Page                | ✅ Completed |
| Property Listing         | ✅ Completed |
| Property Search          | ✅ Completed |
| Search Filters           | ✅ Completed |
| Server-side Pagination   | ✅ Completed |
| Property Details         | ✅ Completed |
| Property Images          | ✅ Completed |
| Amenities                | ✅ Completed |
| Google Map Embed         | ✅ Completed |
| Sample Data              | ✅ Completed |
| H2 Development Database  | ✅ Completed |
| MySQL Configuration      | ✅ Completed |
| PostgreSQL Configuration | ✅ Completed |
| Docker Support           | ✅ Completed |
| Docker Compose           | ✅ Completed |
| Error Handling           | ✅ Completed |
| User Authentication      | 🚧 Planned  |
| Favourites               | 🚧 Planned  |
| Enquiry Persistence      | 🚧 Planned  |
| Admin Dashboard          | 🚧 Planned  |
| Property CRUD            | 🚧 Planned  |
| Image Upload             | 🚧 Planned  |
| Google Maps API          | 🚧 Planned  |

---

# ⚠️ Project Disclaimer

PropertyWale is a **Bachelor's degree learning project** created for educational and practical learning purposes.

The application is currently under development and should not be considered a fully production-ready real-estate platform.

Additional security, validation, authentication, testing, monitoring, database optimization, and production infrastructure would be required before using the application in a real-world environment.

---

# 👨‍💻 Author

**Purushottam Thakur**

Bachelor's Degree Learning Project

---

# ⭐ Acknowledgement

This project was created as part of my learning journey in **Java, Spring Boot, web application development, and database management**.

Building PropertyWale helped me understand how a real-world Spring Boot application can be structured using controllers, services, repositories, entities, DTOs, database specifications, and server-side templates.

The project is being developed incrementally, with additional features planned for future stages.

---

## 📄 License

This project is created primarily for **educational and learning purposes**.
