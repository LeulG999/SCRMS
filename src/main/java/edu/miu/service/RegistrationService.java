package edu.miu.service;

import edu.miu.domain.AcademicLevel;
import edu.miu.domain.Course;
import edu.miu.domain.Student;
import edu.miu.repository.CourseRepository;
import edu.miu.repository.StudentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class RegistrationService {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public RegistrationService(StudentRepository studentRepository, CourseRepository courseRepository) {
        this.studentRepository = Objects.requireNonNull(studentRepository, "studentRepository must not be null");
        this.courseRepository = Objects.requireNonNull(courseRepository, "courseRepository must not be null");
    }

    public List<Student> getAllStudentsSortedByGpaDescending() {
        return studentRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .toList();
    }

    public List<Student> getGoldStudentsSortedByGpaDescending() {
        return studentRepository.findAll().stream()
                .filter(student -> student.getAcademicLevel() == AcademicLevel.GOLD)
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .toList();
    }

    public List<Course> getAllCoursesSortedByEnrollmentDescending() {
        return courseRepository.findAll().stream()
                .sorted(Comparator.comparingInt((Course course) -> course.getEnrolledStudents().size()).reversed())
                .toList();
    }

    public int getUniversityEnrollmentCount() {
        return studentRepository.findAll().stream()
                .mapToInt(student -> student.getRegisteredCourses().size())
                .sum();
    }
}
