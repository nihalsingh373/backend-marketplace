# Backend Marketplace – Spring Boot 

A production-grade **Spring Boot backend application** for an e-commerce / marketplace system, built with **clean architecture, security, pagination, and best practices**.

This project demonstrates how a real-world backend system is designed, secured, and deployed.

---

## 🚀 Key Features

### 🔐 Authentication & Security
- JWT-based authentication (Stateless)
- Spring Security with custom filters
- Role-based authorization
- Password hashing & encoding
- Secure login / signup / logout APIs

### 📦 Core Business Modules
- Category management
- Product management
- User & Role management
- Seller & Address handling
- Order & Cart related services

### 🧠 Backend Best Practices
- DTO pattern for request/response separation
- ModelMapper for clean data mapping
- Global exception handling
- Custom exceptions (ResourceNotFound, APIException, etc.)
- Validation using Hibernate Validator
- Clean controller–service–repository layering

### 📄 Pagination & Sorting
- Pageable & PageRequest support
- Sorting on multiple fields
- Consistent API response structure
- Default pagination values

### 🗄️ Database & ORM
- Spring Data JPA & Hibernate
- Entity relationships:
  - One-to-One
  - One-to-Many
  - Many-to-Many
- Lazy & Eager fetching
- Cascade operations
- PostgreSQL / H2 support

### 🐳 Docker & Deployment
- Dockerized Spring Boot application
- PostgreSQL container integration
- Environment-based configuration
- AWS Elastic Beanstalk deployment
- Amazon RDS integration

---

## 🏗️ Tech Stack

- **Backend:** Java 21, Spring Boot
- **Security:** Spring Security, JWT
- **Database:** PostgreSQL, H2
- **ORM:** Spring Data JPA, Hibernate
- **Build Tool:** Maven
- **Tools:** Postman, IntelliJ IDEA
- **Containerization:** Docker
- **Cloud:** AWS (RDS, Elastic Beanstalk)

---

## 📁 Project Structure
sb-ecom/
├── controller
├── service
├── repository
├── entity
├── dto
├── security
├── exception
├── config
└── util


---

## 🔄 API Capabilities

- RESTful APIs following HTTP standards
- Proper status codes & response entities
- JSON-based request/response
- Secure endpoints with JWT
- Pagination & filtering support

---

## 🌱 What This Project Demonstrates

✔ Strong understanding of **backend fundamentals**  
✔ Real-world **Spring Boot architecture**  
✔ Secure API design  
✔ Database modeling & relationships  
✔ Scalable & maintainable codebase  
✔ Production-ready mindset  

---

## 📌 Future Enhancements
- Split into microservices architecture
- API Gateway integration
- Service discovery & fault tolerance
- CI/CD pipeline

---

## 👤 Author

**Nihal Singh**  
Backend Developer | Java | Spring Boot | Microservices

---

> 💡 This project is part of my backend engineering journey and reflects industry-level development practices.

