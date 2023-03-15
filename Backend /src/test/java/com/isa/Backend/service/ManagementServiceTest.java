//package com.isa.Backend.service;
//
//import com.isa.Backend.dto.AuthenticationResponse;
//import com.isa.Backend.dto.RegisterRequest;
//import com.isa.Backend.model.Role;
//import com.isa.Backend.model.Users;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//public class ManagementServiceTest {
//
//    @Mock
//    private AuthenticationService authenticationService;
//
//    private ManagementService managementService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        managementService = new ManagementService(authenticationService);
//    }
//
//    @Test
//    void addManager() {
//        // Arrange
//        String email = "example@test.com";
//        String password = "password";
//        RegisterRequest registerRequest = new RegisterRequest();
//        registerRequest.setEmail(email);
//        registerRequest.setPassword(password);
//        registerRequest.setRole(Role.MANAGEMENT);
//        Users user = new Users();
//        user.setEmail(email);
//        user.setRole(Role.MANAGEMENT);
//        AuthenticationResponse expectedResponse = new AuthenticationResponse();
//        expectedResponse.setToken("token");
//        expectedResponse.setUser(user);
//        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);
//
//        // Act
//        AuthenticationResponse result = managementService.addManager(registerRequest);
//
//        // Assert
//        Assertions.assertEquals(expectedResponse.getToken(), result.getToken());
//        Assertions.assertEquals(expectedResponse.getUser().getEmail(), result.getUser().getEmail());
//        Assertions.assertEquals(expectedResponse.getUser().getRole(), result.getUser().getRole());
//    }
//}
package com.isa.Backend.service;

import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.dto.RegisterResponse;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ManagementServiceTest {

    @Mock
    private AuthenticationService authenticationService;

    private ManagementService managementService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        managementService = new ManagementService(authenticationService);
    }

    @Test
    void addManager() {
        // Arrange
        String email = "example@test.com";
        String password = "password";
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail(email);
        registerRequest.setPassword(password);
        registerRequest.setRole(Role.MANAGEMENT);
        Users user = new Users();
        user.setEmail(email);
        user.setRole(Role.MANAGEMENT);
        RegisterResponse expectedResponse = new RegisterResponse();

        expectedResponse.setUser(user);
        when(authenticationService.register(registerRequest)).thenReturn(expectedResponse);

        // Act
        RegisterResponse result = managementService.addManager(registerRequest);

        // Assert

        Assertions.assertEquals(expectedResponse.getUser().getEmail(), result.getUser().getEmail());
        Assertions.assertEquals(expectedResponse.getUser().getRole(), result.getUser().getRole());
    }
}
