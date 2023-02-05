package com.isa.Backend.controller;

import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
