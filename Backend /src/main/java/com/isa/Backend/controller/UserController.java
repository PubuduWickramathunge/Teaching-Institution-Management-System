package com.isa.Backend.controller;

import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller

public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping("/login")
    @ResponseBody
    public String viewHomePage() {
        return "index";

    }

    @GetMapping("")
    @ResponseBody

    public String firstPage() {
        return "No Password";
    }

    @GetMapping("/users")
    @ResponseBody
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
