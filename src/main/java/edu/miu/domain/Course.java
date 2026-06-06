package edu.miu.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Course {
    private final String courseId;
    private final String courseCode;
    private final String title;
    private final int creditHours;
    private final List<Student> enrolledStudents;

    public Course(String courseId, String courseCode, String title, int creditHours) {
        this.courseId = requireText(courseId, "courseId");
        this.courseCode = requireText(courseCode, "courseCode");
        this.title = requireText(title, "title");
        if (creditHours <= 0) {
            throw new IllegalArgumentException("creditHours must be greater than zero");
        }
        this.creditHours = creditHours;
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public int getCreditHours() {
        return creditHours;
    }

    public List<Student> getEnrolledStudents() {
        return Collections.unmodifiableList(enrolledStudents);
    }

    public void enrollStudent(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            student.addRegisteredCourse(this);
        }
    }

    void addEnrolledStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
        }
    }

    private static String requireText(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException(fieldName + " must not be blank");
        }
        return value;
    }
}
