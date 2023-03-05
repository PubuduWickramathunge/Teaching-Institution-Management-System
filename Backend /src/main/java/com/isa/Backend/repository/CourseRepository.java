package com.isa.Backend.repository;

import com.isa.Backend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course save(Course course);
    List<Course> findByTeacherId(Long teacherId);
    Optional<Course> findByIdAndTeacherId(Long id, Long teacherId);

}