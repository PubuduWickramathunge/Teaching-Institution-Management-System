package com.isa.Backend.controller;

import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagementController {
    private final ManagementService managementService;


    @PostMapping("/addmanager")
    public ResponseEntity<?> addManager(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(managementService.addManager(registerRequest));
    }

}
