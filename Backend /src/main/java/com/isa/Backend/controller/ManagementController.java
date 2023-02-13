package com.isa.Backend.controller;

import com.isa.Backend.dto.ManagementRegRequest;
import com.isa.Backend.model.Management;
import com.isa.Backend.service.ManagementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequiredArgsConstructor
public class ManagementController {
    private final ManagementService managementService;


    @PostMapping("/register/management")

    public ResponseEntity<Management> addManagementDetails(@RequestBody ManagementRegRequest management) throws ParseException {

        Management addedManagement = new Management(management.getPosition(), management.getMobile(), management.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(managementService.updateManagement(addedManagement));
    }
}