package com.isa.Backend.controller;

import com.isa.Backend.exception.ProfileNotFoundException;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller

public class UserController {

    public final UserRepository userRepository;
    public final AuthenticationService authenticationService;


    public UserController(UserRepository userRepository, AuthenticationService authenticationService) {
        this.userRepository = userRepository;
        this.authenticationService = authenticationService;
    }

    @GetMapping("/profile")
    public ResponseEntity<Users> viewProfile(@RequestParam String email) throws ProfileNotFoundException {
        return authenticationService.viewProfile(email)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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


}
