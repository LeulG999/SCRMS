package edu.miu.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Student {
    private final String studentId;
    private final String firstName;
    private final String lastName;
    private final double gpa;
    private final int completedCredits;
    private final List<Course> registeredCourses;

    public Student(String studentId, String firstName, String lastName, double gpa, int completedCredits) {
        this.studentId = requireText(studentId, "studentId");
        this.firstName = requireText(firstName, "firstName");
        this.lastName = requireText(lastName, "lastName");
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("gpa must be between 0.0 and 4.0");
        }
        if (completedCredits < 0) {
            throw new IllegalArgumentException("completedCredits must not be negative");
        }
        this.gpa = gpa;
        this.completedCredits = completedCredits;
        this.registeredCourses = new ArrayList<>();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getGpa() {
        return gpa;
    }

    public int getCompletedCredits() {
        return completedCredits;
    }

    public List<Course> getRegisteredCourses() {
        return Collections.unmodifiableList(registeredCourses);
    }

    public AcademicLevel getAcademicLevel() {
        if (completedCredits >= 90 && gpa >= 3.5) {
            return AcademicLevel.GOLD;
        }
        if (completedCredits >= 45 && gpa >= 3.0) {
            return AcademicLevel.SILVER;
        }
        if (completedCredits >= 15 && gpa >= 2.0) {
            return AcademicLevel.BRONZE;
        }
        return AcademicLevel.REGULAR;
    }

    public void registerCourse(Course course) {
        Objects.requireNonNull(course, "course must not be null");
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
            course.addEnrolledStudent(this);
        }
    }

    void addRegisteredCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            registeredCourses.add(course);
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }
}
