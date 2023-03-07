package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.repository.CourseRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }


    public Course createCourse(Course course) {
        try {
            return courseRepository.save(course);
        } catch (DataIntegrityViolationException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Course with name already exists", e);
        }
    }
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }
    public List<Course> searchCoursesByName(String name) {
        return courseRepository.findByNameContainingIgnoreCase(name);
    }

}
