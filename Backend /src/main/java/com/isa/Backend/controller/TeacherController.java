package com.isa.Backend.controller;

import com.isa.Backend.exception.ResourceNotFoundException;
import com.isa.Backend.model.Course;
import com.isa.Backend.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teachers/{teacherId}/courses")
public class TeacherController {


    private final CourseRepository courseRepository;

    public TeacherController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping
    public List<Course> getCoursesOfTeacher(@PathVariable Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long teacherId, @PathVariable Long courseId) {
        return courseRepository.findByIdAndTeacherId(courseId, teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
}

