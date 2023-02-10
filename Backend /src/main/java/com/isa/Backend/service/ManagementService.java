package com.isa.Backend.service;

import com.isa.Backend.model.Management;
import com.isa.Backend.repository.ManagementRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagementService {

    private final ManagementRepository managementRepository;
    private final UserRepository userRepository;

    public ManagementService(ManagementRepository managementRepository, UserRepository userRepository) {
        this.managementRepository = managementRepository;
        this.userRepository = userRepository;
    }

    public Management createManagement(Management management) {
        return managementRepository.save(management);
    }

    public Management getManagementById(Long id) {
        return managementRepository.findById(id).orElse(null);
    }

    public List<Management> getAllManagement() {
        return managementRepository.findAll();
    }

    public Management updateManagement(Management management) {
//        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
//        Management management = new Management();
//        management.setUser(user);
        return managementRepository.save(management);
    }
}