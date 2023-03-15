package com.isa.Backend.controller;

import com.isa.Backend.exception.ResourceNotFoundException;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    @InjectMocks
    private TeacherController teacherController;

    private List<Course> courses;
    private Set<Users> students;
    private Course course;
    private Long teacherId;
    private Long courseId;

    @BeforeEach
    void setUp() {
        teacherId = 1L;
        courseId = 2L;
        Users teacher = new Users(teacherId, "John", "Doe", "john.doe@gmail.com", "password", Role.TEACHER, new HashSet<>());
        courses = new ArrayList<>();
        course = new Course(courseId, "Course 1", "Description of course 1", teacher, students);
        courses.add(course);
    }

    @Test
    void testGetCoursesOfTeacher() {
        when(teacherService.getCoursesOfTeacher(anyLong())).thenReturn(courses);
        List<Course> returnedCourses = teacherController.getCoursesOfTeacher(teacherId);
        assertEquals(courses, returnedCourses);
    }

    @Test
    void testGetCourseById() {
        when(teacherService.getCourseById(anyLong(), anyLong())).thenReturn(course);
        Course returnedCourse = teacherController.getCourseById(teacherId, courseId);
        assertEquals(course, returnedCourse);
    }

    @Test
    void testGetCourseByIdNotFound() {
        when(teacherService.getCourseById(anyLong(), anyLong())).thenThrow(new ResourceNotFoundException("Course not found"));
        assertThrows(ResourceNotFoundException.class, () -> teacherController.getCourseById(teacherId, courseId));
    }

    @Test
    void testGetStudentsInCourse() {
        students = new HashSet<>();
        Users student1 = new Users(2L, "Jane", "Doe", "jane.doe@gmail.com", "password", Role.STUDENT, new HashSet<>());
        Users student2 = new Users(3L, "Bob", "Smith", "bob.smith@gmail.com", "password", Role.STUDENT, new HashSet<>());
        students.add(student1);
        students.add(student2);
        course.setStudents(students);
        when(teacherService.getStudentsInCourse(anyLong(), anyLong())).thenReturn(students);
        Set<Users> returnedStudents = teacherController.getStudentsInCourse(teacherId, courseId);
        assertEquals(students, returnedStudents);
    }

    @Test
    void testGetStudentsInCourseNotFound() {
        when(teacherService.getStudentsInCourse(anyLong(), anyLong())).thenThrow(new ResourceNotFoundException("Course not found"));
        assertThrows(ResourceNotFoundException.class, () -> teacherController.getStudentsInCourse(teacherId, courseId));
    }
}
