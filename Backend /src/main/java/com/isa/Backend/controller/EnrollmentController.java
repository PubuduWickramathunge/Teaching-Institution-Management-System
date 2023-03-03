package com.isa.Backend.controller;

import com.isa.Backend.model.Course;
import com.isa.Backend.service.EnrollmentService;
import io.jsonwebtoken.Jwt;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {


    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
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
    public ResponseEntity<Set<Course>> getAllCoursesForStudent(Authentication authentication) {
        Set<Course> courses = enrollmentService.getAllCoursesForStudent(authentication.getName());

        if (courses != null) {
            return ResponseEntity.ok(courses);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}
