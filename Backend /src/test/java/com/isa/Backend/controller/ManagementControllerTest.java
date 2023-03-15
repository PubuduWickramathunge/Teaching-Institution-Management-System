package com.isa.Backend.controller;


import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.dto.RegisterResponse;
import com.isa.Backend.service.ManagementService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ManagementControllerTest {

    private ManagementController managementController;

    @Mock
    private ManagementService managementService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        managementController = new ManagementController(managementService);
    }

    @Test
    public void addManagerTest() {
        // Given
        RegisterRequest registerRequest = new RegisterRequest();
        when(managementService.addManager(registerRequest)).thenReturn(new RegisterResponse());

        // When
        ResponseEntity<?> responseEntity = managementController.addManager(registerRequest);

        // Then
        verify(managementService, times(1)).addManager(registerRequest);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
