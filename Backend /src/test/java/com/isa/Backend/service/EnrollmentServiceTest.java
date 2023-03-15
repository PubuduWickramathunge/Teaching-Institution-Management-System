package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EnrollmentServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testEnrollStudent() throws Exception {
        // Setup mock objects
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");

        Users student = new Users();
        student.setEmail("test@test.com");
        student.setCourses(new HashSet<>());

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(student));
        when(userRepository.save(any(Users.class))).thenReturn(student);

        // Call the method being tested
        enrollmentService.enrollStudent(1L, "test@test.com");

        // Verify the expected changes were made
        Set<Course> courses = student.getCourses();
        assertEquals(1, courses.size());
        assertEquals(course, courses.iterator().next());

        verify(userRepository).save(student);
    }

    @Test
    public void testEnrollStudentAlreadyEnrolled() throws Exception {
        // Setup mock objects
        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course");

        Users student = new Users();
        student.setEmail("test@test.com");
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        student.setCourses(courses);

        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
        when(userRepository.findByEmail("test@test.com")).thenReturn(Optional.of(student));

        // Call the method being tested
        Exception exception = assertThrows(Exception.class, () -> {
            enrollmentService.enrollStudent(1L, "test@test.com");
        });

        // Verify the expected exception was thrown
        assertEquals("You have already enrolled in the course.", exception.getMessage());

        // Verify no changes were made
        verify(userRepository, never()).save(any(Users.class));
    }

    @Test
    public void testUnenrollStudent() {
        // create a sample course and student
        Course course = new Course();
        course.setId(1L);
        course.setName("Math");

        Users student = new Users();
        student.setId(1L);
        student.setEmail("John@Example.com");
        Set<Course> courses = new HashSet<>();
        courses.add(course);
        student.setCourses(courses);


                // mock the course and student repositories to return the sample data
            when(courseRepository.findById(1L)).thenReturn(Optional.of(course));
            when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(student));

            // unenroll the student from the course
            enrollmentService.unenrollStudent(1L, "john@example.com");

            // verify that the course was removed from the student's courses
            verify(userRepository, times(1)).save(student);
            assertEquals(0, student.getCourses().size());
        }

}
