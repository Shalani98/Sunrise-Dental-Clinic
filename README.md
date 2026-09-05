Sunrise Dental Clinic Management System

A Java web-based clinic management system developed for Sunrise Dental Clinic to manage patient appointments, billing, users, and clinic operations in a structured and secure way.

The system was developed as part of the CIS6003 – Advanced Programming module for the B.Sc. (Hons) in Software Engineering programme.

Project Overview

The Sunrise Dental Clinic Management System replaces manual clinic processes with a computerized web application.

It allows authorized staff to:

Log in securely

Register patients

Create dental appointments

Search and view appointments

Generate patient bills

Update appointment payment status

Generate PDF receipts

Access help information

Log out securely

Administrators can also manage system users.

Main Features

Authentication and Security

Secure user login

BCrypt password hashing and verification

Automatic migration of older plain-text passwords to BCrypt hashes

Session-based authentication

Role-based authorization

Authentication filter for protected resources

Admin authorization filter

Session invalidation on logout

Patient and Appointment Management

Register patient information

Create dental appointments

Select dentist and treatment

Enter appointment date and time

View appointments

Search appointments by appointment number

Update appointment information

Billing

Generate bills for appointments

Prevent duplicate bill creation

Calculate consultation fee, treatment cost, and total amount

Perform server-side billing validation

Update appointment status to Paid

Generate printable PDF receipts

User Management

Add users

View users

Update users

Delete users

Restrict administrative operations to authorized users

Technologies Used

Area

Technology

Frontend

HTML, CSS, JavaScript

Backend

Java Servlets

Application Layer

Java Service Classes

Database Access

DAO Pattern, JDBC, PreparedStatement

Database

MySQL

Build Tool

Maven

Testing

JUnit 5, Mockito

Test Execution

Maven Surefire Plugin

PDF Generation

iText

Password Security

BCrypt

Version Control

Git and GitHub

Continuous Integration

GitHub Actions

System Architecture

The application follows a three-tier architecture:

Browser
   ↓
Servlet Controller
   ↓
Service Layer
   ↓
DAO Layer
   ↓
MySQL Database

Presentation Tier

HTML, CSS and JavaScript browser interfaces.

Business Logic Tier

Java Servlets act as controllers, while service classes coordinate application processing.

Data Tier

DAO classes use JDBC and PreparedStatement to communicate with the MySQL database.

Design Patterns and Approaches

DAO Pattern – separates database operations from application logic

Service Layer Pattern – separates controller logic from business processing

MVC-style structure – separates model, view and controller responsibilities

Singleton Pattern – centralizes database connection management

Layered Architecture – improves separation of concerns and maintainability

Main Components

Controllers

LoginServlet

LogoutServlet

AppointmentServlet

SearchAppointmentServlet

BillServlet

PrintBillServlet

DentistServlet

TreatmentServlet

UserServlet

Service Classes

LoginService

AppointmentService

PatientService

BillingService

DentistService

TreatmentService

DAO Classes

UserDAO

PatientDAO

AppointmentDAO

DentistDAO

TreatmentDAO

BillDAO

UML Diagrams

The diagrams folder contains the final UML diagrams:

Use Case Diagram

Class Diagram

Login Sequence Diagram

Register Patient and Create Appointment Sequence Diagram

Generate Bill Sequence Diagram

Print PDF Receipt Sequence Diagram

Database

The application uses a MySQL database named:

sunrise_dental

Main data areas include:

Users

Patients

Dentists

Treatments

Appointments

Bills

Project Structure

Sunrise-Dental-Clinic/
│
├── .github/
│   └── workflows/
├── database/
├── diagrams/
├── src/
├── .gitignore
├── pom.xml
└── README.md

Requirements

Install:

Java Development Kit (JDK)

Apache Maven

Apache Tomcat

MySQL Server

Git

A Java IDE such as IntelliJ IDEA, Eclipse or NetBeans can also be used.

Installation and Setup

1. Clone the repository

git clone https://github.com/Shalani98/Sunrise-Dental-Clinic.git

2. Open the project

Open the project in your preferred Java IDE.

3. Configure the database

Create the MySQL database and import the SQL schema in the database folder. Update the database connection settings if necessary.

4. Build the project

mvn clean package

5. Deploy

Deploy the generated WAR file to Apache Tomcat and open the application in a web browser.

Testing

Automated testing uses:

JUnit 5

Mockito

Maven Surefire Plugin

Run the tests with:

mvn clean test

Test reports are generated in:

target/surefire-reports/

Continuous Integration

GitHub Actions is configured to automatically run Maven builds and automated tests when changes are pushed to the repository.

Workflow files are stored in:

.github/workflows/

Version Control

Git and GitHub are used to maintain the project source code and development history.

The repository includes:

Initial repository creation

Source code updates

Testing configuration

GitHub Actions CI workflow

Database schema versioning

Final UML diagram updates

Security Considerations

The project includes:

BCrypt password hashing

Password verification during login

Authentication filtering

Administrative authorization

Session management

Logout session invalidation

Server-side validation

Prepared statements for database access

Future Improvements

Possible future enhancements:

Advanced reports and analytics

Email or SMS appointment reminders

Online appointment booking

Dentist-specific dashboards

Patient treatment history

Database-level uniqueness constraints for billing

Transaction management for patient and appointment creation

Audit logging

Mobile-responsive interface improvements

Author

K.H. Shalani Nethmini
B.Sc. (Hons) in Software Engineering

Academic Use

This project was developed for academic purposes as part of the CIS6003 – Advanced Programming module.
