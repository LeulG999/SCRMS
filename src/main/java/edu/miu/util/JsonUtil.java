package edu.miu.util;

import edu.miu.domain.Course;
import edu.miu.domain.Student;

import java.util.List;
import java.util.stream.Collectors;

public final class JsonUtil {
    private JsonUtil() {
    }

    public static String studentsToJson(List<Student> students, int universityEnrollmentCount) {
        String jsonStudents = students.stream()
                .map(JsonUtil::studentToJson)
                .collect(Collectors.joining(",\n    "));

        return "{\n"
                + "  \"students\": [\n"
                + "    " + jsonStudents + "\n"
                + "  ],\n"
                + "  \"universityEnrollmentCount\": " + universityEnrollmentCount + "\n"
                + "}";
    }

    public static String coursesToJson(List<Course> courses) {
        String jsonCourses = courses.stream()
                .map(JsonUtil::courseToJson)
                .collect(Collectors.joining(",\n    "));

        return "{\n"
                + "  \"courses\": [\n"
                + "    " + jsonCourses + "\n"
                + "  ]\n"
                + "}";
    }

    private static String studentToJson(Student student) {
        String jsonCourses = student.getRegisteredCourses().stream()
                .map(JsonUtil::courseSummaryToJson)
                .collect(Collectors.joining(", "));

        return "{"
                + "\"studentId\": \"" + escape(student.getStudentId()) + "\", "
                + "\"firstName\": \"" + escape(student.getFirstName()) + "\", "
                + "\"lastName\": \"" + escape(student.getLastName()) + "\", "
                + "\"gpa\": " + student.getGpa() + ", "
                + "\"completedCredits\": " + student.getCompletedCredits() + ", "
                + "\"academicLevel\": \"" + student.getAcademicLevel() + "\", "
                + "\"registeredCourses\": [" + jsonCourses + "]"
                + "}";
    }

    private static String courseToJson(Course course) {
        return "{"
                + "\"courseId\": \"" + escape(course.getCourseId()) + "\", "
                + "\"courseCode\": \"" + escape(course.getCourseCode()) + "\", "
                + "\"title\": \"" + escape(course.getTitle()) + "\", "
                + "\"creditHours\": " + course.getCreditHours() + ", "
                + "\"enrolledStudentCount\": " + course.getEnrolledStudents().size()
                + "}";
    }

    private static String courseSummaryToJson(Course course) {
        return "{"
                + "\"courseId\": \"" + escape(course.getCourseId()) + "\", "
                + "\"courseCode\": \"" + escape(course.getCourseCode()) + "\", "
                + "\"title\": \"" + escape(course.getTitle()) + "\""
                + "}";
    }

    private static String escape(String value) {
        return value.replace("\\", "\\\\").replace("\"", "\\\"");
    }
}
