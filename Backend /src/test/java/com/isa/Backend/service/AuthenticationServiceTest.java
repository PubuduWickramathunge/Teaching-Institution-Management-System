package com.isa.Backend.service;

import com.isa.Backend.dto.AuthenticationRequest;
import com.isa.Backend.dto.AuthenticationResponse;
import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.exception.CustomAuthenticationException;
import com.isa.Backend.exception.UserAlreadyExistsException;
import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthenticationServiceTest {



    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @InjectMocks
    private AuthenticationService authenticationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testRegisterSuccess() throws UserAlreadyExistsException {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@test.com", "password", Role.STUDENT);
        Users user = Users.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.empty());
        when(userRepository.save(any(Users.class))).thenReturn(user);


        // Act
        AuthenticationResponse response = AuthenticationResponse.builder().user(user).build();

        // Assert

        assertEquals(user, response.getUser());
    }

    @Test
    void testRegisterUserAlreadyExists() {
        // Arrange
        RegisterRequest registerRequest = new RegisterRequest("John", "Doe", "john.doe@test.com", "password", Role.STUDENT);
        when(userRepository.findByEmail(registerRequest.getEmail())).thenReturn(Optional.of(new Users()));

        // Act and Assert
        assertThrows(UserAlreadyExistsException.class, () -> authenticationService.register(registerRequest));
    }







    @Test
    void testAuthenticateWithValidCredentials() {
        // given
        String email = "user@example.com";
        String password = "password";
        Users user = Users.builder()
                .id(1L)
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.MANAGEMENT)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        when(jwtService.generateToken(user)).thenReturn("jwtToken");
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);

        // when
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);

        // then
        assertNotNull(response);
        assertEquals("jwtToken", response.getToken());
        assertEquals(user, response.getUser());
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testAuthenticateWithInvalidCredentials() {
        // given
        String email = "user@example.com";
        String password = "password";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenThrow(new BadCredentialsException("Invalid username or password"));

        // when
        assertThrows(CustomAuthenticationException.class, () -> authenticationService.authenticate(authenticationRequest));

        // then
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }

    @Test
    void testAuthenticateWithNonExistingUser() {
        // given
        String email = "nonexistinguser@example.com";
        String password = "password";
        AuthenticationRequest authenticationRequest = new AuthenticationRequest(email, password);
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // when
        assertThrows(CustomAuthenticationException.class, () -> authenticationService.authenticate(authenticationRequest));

        // then
        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
    }


    @Test
    public void testViewProfile() {
        // Arrange
        String email = "test@test.com";
        Users user = Users.builder()
                .id(1L)
                .firstName("Test")
                .lastName("User")
                .email(email)
                .password("test")
                .role(Role.MANAGEMENT)
                .build();
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Optional<Users> result = authenticationService.viewProfile(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(user, result.get());
    }



}
