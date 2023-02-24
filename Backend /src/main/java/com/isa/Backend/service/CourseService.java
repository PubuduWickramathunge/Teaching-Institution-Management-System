package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }
}
