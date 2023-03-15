package com.isa.Backend.controller;

import com.isa.Backend.config.UserRequestScopedBean;
import com.isa.Backend.dto.AuthenticationRequest;
import com.isa.Backend.dto.AuthenticationResponse;
import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.dto.RegisterResponse;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.AuthenticationService;
import com.isa.Backend.service.BlackListingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AuthenticationControllerTest {

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private Authentication authentication;

    @Mock
    private UserRequestScopedBean userRequestScopedBean;

    @Mock
    private BlackListingService blackListingService;

    @InjectMocks
    private AuthenticationController authenticationController;

    private RegisterRequest registerRequest;

    private AuthenticationRequest authenticationRequest;

    private Users user;

    @BeforeEach
    public void setUp() {
        registerRequest = new RegisterRequest();
        registerRequest.setFirstname("John");
        registerRequest.setLastname("Doe");
        registerRequest.setEmail("john.doe@test.com");
        registerRequest.setPassword("password");
        registerRequest.setRole(Role.STUDENT);

        authenticationRequest = new AuthenticationRequest();
        authenticationRequest.setEmail("john.doe@test.com");
        authenticationRequest.setPassword("password");

        user = new Users();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@test.com");
        user.setPassword("password");
        user.setRole(Role.STUDENT);
    }

    @Test
    public void testRegister() {
        RegisterResponse registerResponse = new RegisterResponse(user);
        when(authenticationService.register(any(RegisterRequest.class))).thenReturn(registerResponse);

        ResponseEntity<RegisterResponse> responseEntity = authenticationController.register(registerRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(registerResponse, responseEntity.getBody());
    }

    @Test
    public void testAuthenticate() {
        AuthenticationResponse authenticationResponse = new AuthenticationResponse("token", user);
        when(authenticationService.authenticate(any(AuthenticationRequest.class))).thenReturn(authenticationResponse);

        ResponseEntity<AuthenticationResponse> responseEntity = authenticationController.authenticate(authenticationRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(authenticationResponse, responseEntity.getBody());
    }

    @Test
    public void testViewProfile() {
        when(authentication.getName()).thenReturn("john.doe@test.com");
        when(authenticationService.viewProfile(ArgumentMatchers.anyString())).thenReturn(Optional.of(user));

        ResponseEntity<Users> responseEntity = authenticationController.viewProfile(authentication);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(user, responseEntity.getBody());
    }

    @Test
    void testLogout() {
        // Given
        String jwt = "token";
        when(userRequestScopedBean.getJwt()).thenReturn(jwt);

        // When
        ResponseEntity<Void> response = authenticationController.logout();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(blackListingService).blackListJwt(jwt);
        verify(userRequestScopedBean).setJwt(null);
    }


}
