# Medical Store Management System

A web-based Medical Store Management System developed using **Java, Spring Boot, Thymeleaf, Spring Data JPA, and H2 Database**.

## Features

* User Signup and Login
* Session-based Authentication
* Add Medicine and Available Stock
* Maximum 5 Medicines per User
* View Medicine List
* Display Medicine Added Time
* Search Medicine
* Pagination
* Edit Medicine Details
* Delete Medicine
* Logout

## Technologies Used

* Java 21
* Spring Boot 3.5.6
* Spring MVC
* Spring Data JPA
* Thymeleaf
* H2 Database
* Maven
* HTML
* CSS

## Project Architecture

```text
Browser
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
H2 Database
```

## Project Structure

```text
src/main/java/com/example/medicalstore
│
├── controller
│   ├── AuthController.java
│   └── MedicineController.java
│
├── entity
│   ├── User.java
│   └── Medicine.java
│
├── repository
│   ├── UserRepository.java
│   └── MedicineRepository.java
│
└── service
    ├── UserService.java
    └── MedicineService.java
```

```text
src/main/resources
│
├── templates
│   ├── signup.html
│   ├── login.html
│   ├── medicines.html
│   └── edit.html
│
└── application.properties
```

## Database

This project uses an **H2 file-based database**.

Database URL:

```text
jdbc:h2:file:./data/medicalstoredb
```

H2 Console:

```text
http://localhost:8080/h2-console
```

## How to Run

1. Clone the repository.
2. Open the project in Spring Tools Suite or Eclipse.
3. Make sure Java 21 is configured.
4. Update Maven dependencies.
5. Run `MedicalStoreManagementApplication.java` as a Spring Boot Application.
6. Open the application in a web browser.

Application URL:

```text
http://localhost:8080
```

## Main URLs

```text
Signup:
http://localhost:8080/signup

Login:
http://localhost:8080/login

Medicine Management:
http://localhost:8080/medicines

H2 Console:
http://localhost:8080/h2-console
```

## Author

**Jesifer M**

## Project Type

Full Stack Java / Spring Boot Project
