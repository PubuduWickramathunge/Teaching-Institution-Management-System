package com.isa.Backend.service;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService  {

    private final UserRepository userRepository;

    public List<Users> getAllUsersWithRole(Role role) {
        return userRepository.findByRole(String.valueOf(role));
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
    public Users getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email : " + email));
    }

}
