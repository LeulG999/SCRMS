# Solution Architecture Design Diagram

```mermaid
flowchart TB
    subgraph Presentation["Presentation Layer"]
        CLI["ScrmsCliApplication<br/>- Shows menu<br/>- Reads user choice<br/>- Prints JSON output"]
    end

    subgraph Service["Service Layer"]
        RegistrationService["RegistrationService<br/>Business Logic<br/>- Get all students sorted by GPA<br/>- Get Gold students<br/>- Get courses sorted by enrollment<br/>- Calculate university enrollment count"]
    end

    subgraph Repository["Repository Layer"]
        StudentRepository["StudentRepository<br/>In-memory student storage"]
        CourseRepository["CourseRepository<br/>In-memory course storage"]
    end

    subgraph Domain["Domain Layer"]
        Student["Student Entity<br/>studentId, firstName, lastName,<br/>gpa, completedCredits"]
        Course["Course Entity<br/>courseId, courseCode,<br/>title, creditHours"]
        AcademicLevel["AcademicLevel Enum<br/>REGULAR, BRONZE,<br/>SILVER, GOLD"]
    end

    subgraph Utility["Utility"]
        JsonUtil["JsonUtil<br/>Converts output data to JSON"]
    end

    CLI --> RegistrationService
    RegistrationService --> StudentRepository
    RegistrationService --> CourseRepository
    StudentRepository --> Student
    CourseRepository --> Course
    Student --> Course
    Student --> AcademicLevel
    CLI --> JsonUtil
```

## Layer Responsibilities

| Layer | Responsibility |
| --- | --- |
| Presentation Layer | Handles CLI menu, user input, and displaying output. |
| Service Layer | Contains SCRMS business logic and coordinates repositories. |
| Repository Layer | Stores and retrieves data using in-memory collections. |
| Domain Layer | Defines core entities and academic level rules. |
| Utility | Converts application data into JSON format. |

## OOP Principles Applied

| Principle | How it is applied |
| --- | --- |
| Encapsulation | Entity fields are private and exposed through methods. |
| Abstraction | Repositories hide storage details from the service layer. |
| Single Responsibility | CLI, service, repository, domain, and JSON conversion have separate responsibilities. |
| Object Collaboration | `Student` and `Course` model the many-to-many registration relationship. |
