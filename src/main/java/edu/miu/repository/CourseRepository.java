package edu.miu.repository;

import edu.miu.domain.Course;

import java.util.List;

public interface CourseRepository {
    void save(Course course);

    List<Course> findAll();
}
