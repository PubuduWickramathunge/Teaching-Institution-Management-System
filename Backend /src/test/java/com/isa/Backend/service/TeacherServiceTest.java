package com.isa.Backend.service;

import com.isa.Backend.exception.ResourceNotFoundException;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TeacherServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private TeacherService teacherService;

    private List<Course> courses;
    private Set<Users> students;
    private Users user1;

    @BeforeEach
    public void setUp() {
        courses = new ArrayList<Course>();
        students = new HashSet<Users>();
        user1 = new Users();
        user1.setEmail("user1@test.com");
        user1.setRole(Role.TEACHER);
        // create test data
        Course course1 = new Course(1L, "Course 1","ushfisf", user1, students);
        Course course2 = new Course(2L, "Course 2","kmfprf", user1, students);
        courses.add(course1);
        courses.add(course2);
    }

    @Test
    public void testGetCoursesOfTeacher() {
        // mock the behavior of the repository
        when(courseRepository.findByTeacherId(1L)).thenReturn(courses);

        // call the service method
        List<Course> result = teacherService.getCoursesOfTeacher(1L);

        // assert the result
        assertThat(result).hasSize(2);
        assertThat(result.get(0).getName()).isEqualTo("Course 1");
        assertThat(result.get(1).getName()).isEqualTo("Course 2");
    }

    @Test
    public void testGetCourseById() {
        // create a test course
        Course course1 = new Course(1L, "Course 1","ushfisf", user1, students);

        // mock the behavior of the repository
        when(courseRepository.findByIdAndTeacherId(1L, 1L)).thenReturn(Optional.of(course1));

        // call the service method
        Course result = teacherService.getCourseById(1L, 1L);

        // assert the result
        assertThat(result).isEqualTo(course1);
    }

    @Test
    public void testGetCourseByIdNotFound() {
        // mock the behavior of the repository
        when(courseRepository.findByIdAndTeacherId(1L, 1L)).thenReturn(Optional.empty());

        // assert that the service method throws a ResourceNotFoundException
        assertThatThrownBy(() -> teacherService.getCourseById(1L, 1L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessage("Course not found");
    }

    @Test
    public void testGetStudentsInCourse() {
        // create a test course
        Course course1 = new Course(1L, "Course 1","ushfisf", user1, students);

        // mock the behavior of the repository
        when(courseRepository.findByIdAndTeacherId(1L, 1L)).thenReturn(Optional.of(course1));

        // call the service method
        Set<Users> result = teacherService.getStudentsInCourse(1L, 1L);

        // assert the result
        assertThat(result).isNotNull();
        assertThat(result).isEmpty();
    }
}