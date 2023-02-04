package com.isa.Backend.service;

import com.isa.Backend.controller.AuthenticationService;
import com.isa.Backend.controller.RegisterRequest;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@RequiredArgsConstructor
public class DatabaseLoader {
    private final AuthenticationService authenticationService;

    @Bean
    public void loadSampleData() {
        authenticationService.register(
                new RegisterRequest(
                        "Sadisha",
                        "Nimsara",
                        "sadisha@gmail.com",
                        "123123123",
                        Role.MANAGEMENT
                )
        );
    }
}
