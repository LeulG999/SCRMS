# Student Course Registration Management System (SCRMS)

SCRMS is a simple Java CLI application for managing students, courses, and course registrations.

## Requirements

- Java 17 or higher
- Maven

## Build

```bash
mvn clean package
```

## Run

```bash
java -jar target/SCRMS-1.0-SNAPSHOT.jar
```

## CLI Options

```text
1. Display all students as JSON
2. Display Gold students as JSON
3. Display all courses as JSON
0. Exit
```

## Features

- Displays students with registered courses
- Calculates student academic level: Bronze, Silver, or Gold
- Sorts students by GPA descending
- Displays only Gold students
- Displays courses sorted by enrolled student count descending
- Shows university enrollment count

## Project Structure

```text
src/main/java/edu/miu/
  domain/        Student, Course, AcademicLevel
  repository/    In-memory storage
  service/       Business logic
  presentation/  CLI application
  util/          JSON conversion
```

## Diagrams

- Domain model: `docs/domain-model.drawio`
- Solution architecture: `docs/solution-architecture.drawio`

## Test

```bash
mvn test
```
