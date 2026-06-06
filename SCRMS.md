Objective

Setup and implement a Command-Line Interface (CLI) application project for a University Course Registration System with Software Build Automation, CI/CD, and Containerization.

Problem Description

Assume you have been hired by a university named CS425 University.

The university wants a CLI application called:

Student Course Registration Management System (SCRMS)

The system will be used to manage students and the courses they register for.

Important Business Rules

Students can be categorized into three academic levels:

Bronze Student
Completed at least 15 credits
GPA ≥ 2.0
Silver Student
Completed at least 45 credits
GPA ≥ 3.0
Gold Student
Completed at least 90 credits
GPA ≥ 3.5

The University Registrar also wants the system to display:

University Enrollment Count

This is calculated as:

Total number of course registrations in the system.

Solution Model

A Student can register for one or many Courses.

A Course can have many Students.

Therefore:

Student ↔ Course

Many-to-Many Relationship

Entities
Student

Attributes:

studentId (Primary Key)
firstName
lastName
gpa
completedCredits
Course

Attributes:

courseId (Primary Key)
courseCode
title
creditHours
Required Tasks
Task 1

Discover and specify the system requirements.

Create:

Functional Requirements
Non-Functional Requirements
Assumptions
Task 2

Draw a Domain Model UML Class Diagram.

Include:

Student
Course

Show:

Attributes
Multiplicity relationships
Task 3

Define a Solution Architecture.

Apply:

Separation of Concerns
Layered Architecture
OOP Principles

Draw a Design Diagram showing:

Presentation Layer

CLI

Service Layer

Business Logic

Repository Layer

In-memory Storage

Domain Layer

Entities

Task 4

Implement the application.

You may use:

Java
C#
Python

Use in-memory storage.

Task 5

Implement Build Automation.

Examples:

Maven
Gradle
.NET Build
Make
Task 6

Generate an Executable Artifact.

Examples:

JAR
EXE
Executable Python Package
Task 7

Create README.md

Include:

Runtime requirements
Installation steps
Build commands
Run commands
Task 8

Push project to GitHub.

Task 9

Setup CI/CD.

Every push should:

Build
Run tests
Produce executable artifact

Using:

GitHub Actions
Task 10

Containerize the Application

Create:

Dockerfile

and

docker-compose.yml

Run application using Docker Compose.

Required Features
Feature 1

Display all Students in JSON format.

Requirements:

Include registered courses.
Include Student Academic Level
(Bronze/Silver/Gold).

Sort students by GPA descending.

Also display:

University Enrollment Count

at the bottom.

Feature 2

Display only Gold Students in JSON format.

Feature 3

Display all Courses in JSON format.

Sort by number of enrolled students descending.