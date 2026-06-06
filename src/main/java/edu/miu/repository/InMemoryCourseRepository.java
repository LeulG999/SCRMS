package edu.miu.repository;

import edu.miu.domain.Course;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InMemoryCourseRepository implements CourseRepository {
    private final List<Course> courses = new ArrayList<>();

    @Override
    public void save(Course course) {
        Objects.requireNonNull(course, "course must not be null");
        courses.add(course);
    }

    @Override
    public List<Course> findAll() {
        return List.copyOf(courses);
    }
}
