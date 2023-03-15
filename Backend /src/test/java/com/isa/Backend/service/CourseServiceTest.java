package com.isa.Backend.service;

import com.isa.Backend.dto.CourseRegRequest;
import com.isa.Backend.dto.CourseResponse;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepositoryMock;

    private CourseService courseService;

    @BeforeEach
    public void setup() {
        courseService = new CourseService(courseRepositoryMock);
    }

    @Test
    public void testCreateCourse() {
        // Given
        CourseRegRequest courseRegRequest = new CourseRegRequest("Math", "Math Course", new Users());
        Course addedCourse = new Course(courseRegRequest.getName(), courseRegRequest.getDescription(), courseRegRequest.getTeacher());
        Course savedCourse = new Course( "Math", "Math Course", new Users());
        when(courseRepositoryMock.save(any(Course.class))).thenReturn(savedCourse);

        // When
        CourseResponse result = courseService.createCourse(courseRegRequest);

        // Then
        Assertions.assertEquals(CourseResponse.fromCourse(savedCourse), result);

        // Verify that the save method of the repository was called with the correct argument
        ArgumentCaptor<Course> argumentCaptor = ArgumentCaptor.forClass(Course.class);
        verify(courseRepositoryMock, times(1)).save(argumentCaptor.capture());
        Course capturedArgument = argumentCaptor.getValue();
        Assertions.assertEquals(courseRegRequest.getName(), capturedArgument.getName());
        Assertions.assertEquals(courseRegRequest.getDescription(), capturedArgument.getDescription());
    }

    @Test
    public void testCreateCourseWithDuplicateName() {
        // Given
        CourseRegRequest courseRegRequest = new CourseRegRequest("Math", "Math Course", new Users());
        Course addedCourse = new Course(courseRegRequest.getName(), courseRegRequest.getDescription(), courseRegRequest.getTeacher());
        when(courseRepositoryMock.save(any(Course.class))).thenThrow(new DataIntegrityViolationException("Duplicate key"));

        // When
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class,
                () -> courseService.createCourse(courseRegRequest));

        // Then
        Assertions.assertEquals(HttpStatus.CONFLICT, exception.getStatus());
    }

    @Test
    public void testGetAllCourses() {
        // Given
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Math", "Math Course", new Users()));
        courses.add(new Course("Science", "Science Course", new Users()));
        when(courseRepositoryMock.findAll()).thenReturn(courses);

        // When
        List<Course> result = courseService.getAllCourses();

        // Then
        Assertions.assertEquals(courses, result);
    }

    @Test
    public void testSearchCoursesByName() {
        // Given
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Math", "Math Course", new Users()));
        when(courseRepositoryMock.findByNameContainingIgnoreCase("Math")).thenReturn(courses);

        // When
        List<Course> result = courseService.searchCoursesByName("Math");

        // Then
        Assertions.assertEquals(courses, result);
    }
}
