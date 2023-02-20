package com.isa.Backend.controller;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import com.isa.Backend.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
@RestController
@RequestMapping("/getAll")

public class UserController {

    public final UserRepository userRepository;
    public final AuthenticationService authenticationService;


    public UserController(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }


    @GetMapping("/students")
    public ResponseEntity<List<Users>> getAllStudents() {
        List<Users> students = authenticationService.getAllUsersWithRole(Role.STUDENT);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Users>> getAllTeachers() {
        List<Users> teachers = authenticationService.getAllUsersWithRole(Role.TEACHER);
        return ResponseEntity.ok(teachers);

    }

    @GetMapping("/managers")
    public ResponseEntity<List<Users>> getAllManagers() {
        List<Users> managers = authenticationService.getAllUsersWithRole(Role.MANAGEMENT);
        return ResponseEntity.ok(managers);

    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = authenticationService.getAllUsers();
        return ResponseEntity.ok(users);

    }


}
