package com.isa.Backend.controller;

import com.isa.Backend.model.Course;
import com.isa.Backend.service.EnrollmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EnrollmentControllerTest {

    private EnrollmentController enrollmentController;

    @Mock
    private EnrollmentService enrollmentService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        enrollmentController = new EnrollmentController(enrollmentService);
    }



    @Test
    void enroll_shouldCallEnrollStudentMethodAndReturnOkResponse() throws Exception {
        Long courseId = 1L;
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", null);
        doNothing().when(enrollmentService).enrollStudent(courseId, "test@example.com");

        ResponseEntity<?> responseEntity = enrollmentController.enroll(courseId, authentication);

        verify(enrollmentService).enrollStudent(courseId, "test@example.com");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());


    }

    @Test
    void unenroll_shouldCallUnenrollStudentMethodAndReturnOkResponse() {
        Long courseId = 1L;
        Authentication authentication = new UsernamePasswordAuthenticationToken("test@example.com", null);
        doNothing().when(enrollmentService).unenrollStudent(courseId, "test@example.com");

        ResponseEntity<?> responseEntity = enrollmentController.unenroll(courseId, authentication);

        verify(enrollmentService).unenrollStudent(courseId, "test@example.com");
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }



    @Test
    void testGetEnrolledCourses() {
        Long studentId = 1L;

        Set<Course> expectedCourses = new HashSet<>();
        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Test Course 1");
        expectedCourses.add(course1);
        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Test Course 2");
        expectedCourses.add(course2);

        when(enrollmentService.getEnrolledCourses(studentId)).thenReturn(expectedCourses);

        ResponseEntity<Set<Course>> actualResponseEntity = enrollmentController.getCourses(studentId);

        assertEquals(expectedCourses, actualResponseEntity.getBody());
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
    }

    @Test
    void testGetCourseByIdWhenCourseExists() {
        Long courseId = 1L;

        Course expectedCourse = new Course();
        expectedCourse.setId(1L);
        expectedCourse.setName("Test Course");

        when(enrollmentService.getCourseById(courseId)).thenReturn(expectedCourse);

        ResponseEntity<Course> actualResponseEntity = enrollmentController.getCourseById(courseId);

        assertEquals(expectedCourse, actualResponseEntity.getBody());
        assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
    }

    @Test
    void testGetCourseByIdWhenCourseDoesNotExist() {
        Long courseId = 1L;

        when(enrollmentService.getCourseById(courseId)).thenReturn(null);

        ResponseEntity<Course> actualResponseEntity = enrollmentController.getCourseById(courseId);

        assertEquals(HttpStatus.NOT_FOUND, actualResponseEntity.getStatusCode());
    }
}


