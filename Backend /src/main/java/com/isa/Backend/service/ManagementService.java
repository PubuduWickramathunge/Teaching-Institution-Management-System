package com.isa.Backend.service;

import com.isa.Backend.dto.AuthenticationResponse;
import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ManagementService {
    private final AuthenticationService authenticationService;


    public AuthenticationResponse addManager(RegisterRequest registerRequest) {
        registerRequest.setRole(Role.MANAGEMENT);
        return authenticationService.register(registerRequest);
    }
}
