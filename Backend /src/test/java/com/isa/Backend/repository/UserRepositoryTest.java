package com.isa.Backend.repository;

import com.isa.Backend.model.Users;
import com.isa.Backend.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void findByEmail() {
        // Arrange
        String email = "example@test.com";
        Users user = new Users();
        user.setEmail(email);
        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

        // Act
        Users result = userService.getUserByEmail(email);

        // Assert
        Assertions.assertTrue(result.isCredentialsNonExpired());
        Assertions.assertEquals(email, result.getEmail());
    }



    @Test
    void findAll() {
        // Arrange
        Users user1 = new Users();
        Users user2 = new Users();
        List<Users> expectedUsers = Arrays.asList(user1, user2);
        when(userRepository.findAll()).thenReturn(expectedUsers);

        // Act
        List<Users> result = userService.getAllUsers();

        // Assert
        Assertions.assertEquals(expectedUsers.size(), result.size());
        Assertions.assertEquals(expectedUsers.get(0), result.get(0));
        Assertions.assertEquals(expectedUsers.get(1), result.get(1));
    }
}
