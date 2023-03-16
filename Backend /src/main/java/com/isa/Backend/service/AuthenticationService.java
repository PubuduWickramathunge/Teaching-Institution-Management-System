package com.isa.Backend.service;

import com.isa.Backend.dto.AuthenticationRequest;
import com.isa.Backend.dto.AuthenticationResponse;
import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.dto.RegisterResponse;
import com.isa.Backend.exception.CustomAuthenticationException;
import com.isa.Backend.exception.UserAlreadyExistsException;
import com.isa.Backend.exception.UserNotFoundException;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest registerRequest) throws UserAlreadyExistsException {
        if (userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException("User already exists with email: " + registerRequest.getEmail());
        }
        Users user = Users.builder()
                .firstName(registerRequest.getFirstname())
                .lastName(registerRequest.getLastname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(registerRequest.getRole())
                .build();

        userRepository.save(user);

        return RegisterResponse.builder().user(user).build();


    }


    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            Users user = userRepository.findByEmail(authenticationRequest.getEmail())
                    .orElseThrow(() -> new CustomAuthenticationException("User not found"));


            String jwtToken = jwtService.generateToken(user);
            return AuthenticationResponse.builder().token(jwtToken).user(user).build();

        } catch (AuthenticationException e) {
            throw new CustomAuthenticationException("Invalid username or password");
        }


    }


    public Optional<Users> viewProfile(String email) {

        Users users;
        users = userRepository.findByEmail(email).get();

        return Optional.of(users);
    }


}

