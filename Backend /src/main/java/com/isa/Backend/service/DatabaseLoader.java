package com.isa.Backend.service;

import com.isa.Backend.controller.AuthenticationService;
import com.isa.Backend.controller.RegisterRequest;
import com.isa.Backend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
