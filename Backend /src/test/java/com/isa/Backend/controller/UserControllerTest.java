package com.isa.Backend.controller;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class UserControllerTest {

    @Mock
    private UserService userService;

    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userController = new UserController(null, userService);
    }

    @Test
    void testGetAllStudents() {
        // Create some mock data
        List<Users> students = new ArrayList<>();
        students.add(new Users(1L, "John", "Doe", "johndoe@example.com", "password", Role.STUDENT, null));
        students.add(new Users(2L, "Jane", "Doe", "janedoe@example.com", "password", Role.STUDENT, null));

        // Set up the mock behavior
        when(userService.getAllUsersWithRole(Role.STUDENT)).thenReturn(students);

        // Call the controller method
        ResponseEntity<List<Users>> response = userController.getAllStudents();

        // Check the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Check the response body
        List<Users> responseBody = response.getBody();
        assertEquals(2, responseBody.size());
        assertEquals(students, responseBody);
    }
    @Test
    void testGetAllTeachers() {
        // Create some mock data
        List<Users> teachers = new ArrayList<>();
        teachers.add(new Users(1L, "John", "Doe", "johndoe@example.com", "password", Role.TEACHER, null));
        teachers.add(new Users(2L, "Jane", "Doe", "janedoe@example.com", "password", Role.TEACHER, null));

        // Set up the mock behavior
        when(userService.getAllUsersWithRole(Role.TEACHER)).thenReturn(teachers);

        // Call the controller method
        ResponseEntity<List<Users>> response = userController.getAllTeachers();

        // Check the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Check the response body
        List<Users> responseBody = response.getBody();
        assertEquals(2, responseBody.size());
        assertEquals(teachers, responseBody);
    }

    @Test

    void testGetAllManagers() {
        // Create some mock data
        List<Users> managers = new ArrayList<>();
        managers.add(new Users(1L, "John", "Doe", "johndoe@example.com", "password", Role.MANAGEMENT, null));
        managers.add(new Users(2L, "Jane", "Doe", "janedoe@example.com", "password", Role.MANAGEMENT, null));

        // Set up the mock behavior
        when(userService.getAllUsersWithRole(Role.MANAGEMENT)).thenReturn(managers);

        // Call the controller method
        ResponseEntity<List<Users>> response = userController.getAllManagers();

        // Check the response status
        assertEquals(HttpStatus.OK, response.getStatusCode());

        // Check the response body
        List<Users> responseBody = response.getBody();
        assertEquals(managers, responseBody);
        assertEquals(2, responseBody.size());

    }

    @Test
    void testGetAllUsers() {
        List<Users> users = new ArrayList<>();
        users.add(new Users(1L, "John", "Doe", "johndoe@example.com", "password", Role.STUDENT, null));
        users.add(new Users(2L, "Jane", "Doe", "janedoe@example.com", "password", Role.TEACHER, null));
        users.add(new Users(3L, "Bob", "Smith", "bobsmith@example.com", "password", Role.MANAGEMENT, null));
        when(userService.getAllUsers()).thenReturn(users);

        ResponseEntity<List<Users>> response = userController.getAllUsers();

        assertEquals(users, response.getBody());
    }

    @Test
    void testGetAllStudentsWhenNoStudentAvailable() {
        // Arrange
        when(userService.getAllUsersWithRole(Role.STUDENT)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Users>> response = userController.getAllStudents();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }

    @Test
    void testGetAllTeachersWhenNoTeacherAvailable() {
        // Arrange
        when(userService.getAllUsersWithRole(Role.TEACHER)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Users>> response = userController.getAllTeachers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }


    @Test
    void testGetAllManagersWhenNoManagerAvailable() {
        // Arrange
        when(userService.getAllUsersWithRole(Role.MANAGEMENT)).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Users>> response = userController.getAllManagers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }


    @Test
    void testGetAllUsersWhenNoUserAvailable() {
        // Arrange
        when(userService.getAllUsers()).thenReturn(Collections.emptyList());

        // Act
        ResponseEntity<List<Users>> response = userController.getAllUsers();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }




}

