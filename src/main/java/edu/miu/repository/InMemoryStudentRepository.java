package edu.miu.repository;

import edu.miu.domain.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryStudentRepository implements StudentRepository {
    private final List<Student> students = new ArrayList<>();

    @Override
    public void save(Student student) {
        Objects.requireNonNull(student, "student must not be null");
        students.add(student);
    }

    @Override
    public List<Student> findAll() {
        return List.copyOf(students);
    }
}
