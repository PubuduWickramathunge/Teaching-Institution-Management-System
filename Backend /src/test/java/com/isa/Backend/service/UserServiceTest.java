package com.isa.Backend.service;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ````````````````````````````````````````````````````````````````UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Users user1;
    private Users user2;

    @BeforeEach
    void setUp() {
        user1 = new Users();
        user1.setEmail("user1@test.com");
        user1.setRole(Role.MANAGEMENT);

        user2 = new Users();
        user2.setEmail("user2@test.com");
        user2.setRole(Role.STUDENT);
    }

    @Test
    void getAllUsersWithRole() {
        // Arrange
        Role role = Role.MANAGEMENT;
        List<Users> expectedUsers = Arrays.asList(user1);
        when(userRepository.findByRole(String.valueOf(role))).thenReturn(expectedUsers);

        // Act
        List<Users> result = userService.getAllUsersWithRole(role);

        // Assert
        Assertions.assertEquals(expectedUsers.size(), result.size());
        Assertions.assertEquals(expectedUsers.get(0).getRole(), result.get(0).getRole());
    }

    @Test
    void getAllUsers() {
        // Arrange
        List<Users> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> result = userService.getAllUsers();

        // Assert
        Assertions.assertEquals(expectedUsers.size(), result.size());
        Assertions.assertEquals(expectedUsers.get(0), result.get(0));
        Assertions.assertEquals(expectedUsers.get(1), result.get(1));
    }

    @Test
    void getUserByEmail() {
        // Arrange
        String email = "user1@test.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user1));

        // Act
        Users result = userService.getUserByEmail(email);

        // Assert
        Assertions.assertEquals(user1.getEmail(), result.getEmail());
        Assertions.assertEquals(user1.getRole(), result.getRole());
    }

    @Test
    void getUserByEmailNotFound() {
        // Arrange
        String email = "nonexistent@test.com";
        when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Assert
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            userService.getUserByEmail(email);
        });
    }
}
