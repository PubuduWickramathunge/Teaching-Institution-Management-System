package com.isa.Backend.controller;

import com.isa.Backend.dto.CourseRegRequest;
import com.isa.Backend.dto.CourseResponse;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.CourseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CourseControllerTest {

    @Mock
    private CourseService courseService;

    @InjectMocks
    private CourseController courseController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        courseController = new CourseController(courseService);
    }


    @Test
    public void createCoursesTest() {
        // Given
        Users teacher = new Users(1L,"John", "Doe", "dcoisjois@socd.sdd","pspkfof" ,Role.TEACHER,null);
        CourseRegRequest courseRegRequest = CourseRegRequest.builder()
                .name("Java 101")
                .description("An introduction to Java programming language")
                .teacher(teacher)
                .build();
        Course course = new Course( "Java 101", "An introduction to Java programming language",teacher);

        CourseResponse courseResponse = CourseResponse.fromCourse(course);
        when(courseService.createCourse(any(CourseRegRequest.class))).thenReturn(courseResponse);

        // When
        ResponseEntity<CourseResponse> responseEntity = courseController.createCourses(courseRegRequest);

        // Then
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(courseResponse, responseEntity.getBody());
    }

    @Test
    public void testGetAllCourses() {
        List<Course> courses = new ArrayList<>();
        Users teacher = new Users();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        Course course1 = new Course();
        course1.setId(1L);
        course1.setName("Test Course 1");
        course1.setDescription("This is a test course.");
        course1.setTeacher(teacher);
        courses.add(course1);

        Course course2 = new Course();
        course2.setId(2L);
        course2.setName("Test Course 2");
        course2.setDescription("This is another test course.");
        course2.setTeacher(teacher);
        courses.add(course2);

        List<CourseResponse> expectedCourseResponses = new ArrayList<>();
        expectedCourseResponses.add(CourseResponse.fromCourse(course1));
        expectedCourseResponses.add(CourseResponse.fromCourse(course2));

        when(courseService.getAllCourses()).thenReturn(courses);

        ResponseEntity<List<CourseResponse>> responseEntity = courseController.getAllCourses();

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(expectedCourseResponses);
    }



    @Test
    void searchCourses_shouldReturnListOfCourses() {
        // arrange
        Users teacher = new Users();
        teacher.setId(1L);
        teacher.setFirstName("John");
        teacher.setLastName("Doe");

        Course course = new Course();
        course.setId(1L);
        course.setName("Test Course 1");
        course.setDescription("This is a test course.");
        course.setTeacher(teacher);

        List<Course> courses = new ArrayList<>();
        courses.add(course);
        when(courseService.searchCoursesByName(anyString())).thenReturn(courses);

        // act
        ResponseEntity<List<CourseResponse>> responseEntity = courseController.searchCourses("Test Course 1");

        // assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(courses.size(), responseEntity.getBody().size());
        assertEquals(courses.get(0).getName(), responseEntity.getBody().get(0).getName());
    }

    @Test
    void testSearchCourses_whenCoursesDoNotExist() {
        // Given
        when(courseService.searchCoursesByName(anyString())).thenReturn(new ArrayList<>());

        // When
        ResponseEntity<List<CourseResponse>> responseEntity = courseController.searchCourses("Java");

        // Then
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        List<CourseResponse> courseResponses = responseEntity.getBody();
        assertEquals(0, courseResponses.size());
    }

}
