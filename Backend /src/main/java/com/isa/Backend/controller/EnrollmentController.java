package com.isa.Backend.controller;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.UserRepository;
import com.isa.Backend.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {


    private final EnrollmentService enrollmentService;
    private final UserRepository userRepository;

    private final CourseRepository courseRepository;


    public EnrollmentController(EnrollmentService enrollmentService, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentService = enrollmentService;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<?> enroll(@PathVariable("courseId") Long courseId, Authentication authentication) {

        enrollmentService.enrollStudent(courseId, authentication.getName());
        return ResponseEntity.ok("Student enrolled successfully.");
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> unenroll(@PathVariable("courseId") Long courseId, Authentication authentication) {
        enrollmentService.unenrollStudent(courseId, authentication.getName());
        return ResponseEntity.ok("Student unenrolled successfully.");
    }

    @GetMapping("/courses")
    public ResponseEntity<Set<Course>> getCourses(Authentication authentication) {
        Optional<Users> studentOptional = userRepository.findByEmail(authentication.getName());
        if (studentOptional.isPresent()) {
            Set<Course> courses = studentOptional.get().getCourses();
            return ResponseEntity.ok(courses);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Long courseId) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        if (courseOptional.isPresent()) {
            Course course = courseOptional.get();
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }



}
