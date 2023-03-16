package com.isa.Backend.controller;

import com.isa.Backend.config.UserRequestScopedBean;
import com.isa.Backend.dto.AuthenticationRequest;
import com.isa.Backend.dto.AuthenticationResponse;
import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.dto.RegisterResponse;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.AuthenticationService;
import com.isa.Backend.service.BlackListingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    private final BlackListingService blackListingService;

    private final UserRequestScopedBean userRequestScopedBean;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest authenticationRequest)  {
        return ResponseEntity.ok(authenticationService.authenticate(authenticationRequest));
    }

    @GetMapping("/profile")
    public ResponseEntity<Users> viewProfile(Authentication authentication) {

        return authenticationService.viewProfile(authentication.getName())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/logout")
    public ResponseEntity<Void> logout() {
        String jwt = userRequestScopedBean.getJwt();
        blackListingService.blackListJwt(jwt);
        userRequestScopedBean.setJwt(null);
        return ResponseEntity.ok(null);

    }


}
