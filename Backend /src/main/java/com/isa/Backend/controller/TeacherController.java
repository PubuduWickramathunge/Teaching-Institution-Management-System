package com.isa.Backend.controller;

import com.isa.Backend.exception.ResourceNotFoundException;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/teachers/{teacherId}")
public class TeacherController {


    private final CourseRepository courseRepository;

    public TeacherController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GetMapping("/courses")
    public List<Course> getCoursesOfTeacher(@PathVariable Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long teacherId, @PathVariable Long courseId) {
        return courseRepository.findByIdAndTeacherId(courseId, teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }
    @GetMapping("/{courseId}/students")
    public Set<Users> getStudentsInCourse(@PathVariable Long teacherId, @PathVariable Long courseId) {
        Course course = courseRepository.findByIdAndTeacherId(courseId, teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return course.getStudents();
    }
}

