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

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/{courseId}")
    public ResponseEntity<?> enroll(@PathVariable("courseId") Long courseId, Authentication authentication) throws Exception {
        enrollmentService.enrollStudent(courseId, authentication.getName());
        return ResponseEntity.ok("Student enrolled successfully.");
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> unenroll(@PathVariable("courseId") Long courseId, Authentication authentication) {
        enrollmentService.unenrollStudent(courseId, authentication.getName());
        return ResponseEntity.ok("Student unenrolled successfully.");
    }

    @GetMapping("/{studentId}/courses")
    public ResponseEntity<Set<Course>> getCourses(@PathVariable Long studentId) {
        Set<Course> courses = enrollmentService.getEnrolledCourses(studentId);
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable("courseId") Long courseId) {
        Course course = enrollmentService.getCourseById(courseId);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }
}
