package com.isa.Backend.controller;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import com.isa.Backend.service.UserService;
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
    public final UserService userService;


    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }


    @GetMapping("/students")
    public ResponseEntity<List<Users>> getAllStudents() {
        List<Users> students = userService.getAllUsersWithRole(Role.STUDENT);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/teachers")
    public ResponseEntity<List<Users>> getAllTeachers() {
        List<Users> teachers = userService.getAllUsersWithRole(Role.TEACHER);
        return ResponseEntity.ok(teachers);

    }

    @GetMapping("/managers")
    public ResponseEntity<List<Users>> getAllManagers() {
        List<Users> managers = userService.getAllUsersWithRole(Role.MANAGEMENT);
        return ResponseEntity.ok(managers);

    }

    @GetMapping("/users")
    public ResponseEntity<List<Users>> getAllUsers() {
        List<Users> users = userService.getAllUsers();
        return ResponseEntity.ok(users);

    }


}
