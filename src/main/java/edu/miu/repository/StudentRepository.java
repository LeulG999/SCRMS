package edu.miu.repository;

import edu.miu.domain.Student;

import java.util.List;

public interface StudentRepository {
    void save(Student student);

    List<Student> findAll();
}
