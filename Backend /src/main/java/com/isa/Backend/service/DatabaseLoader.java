package com.isa.Backend.service;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.User;
import com.isa.Backend.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class DatabaseLoader {

    private UserRepository userRepository;

    public DatabaseLoader(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner initializeDatabase(){

        return args -> {
            User pubudu =  new User("pubudu","Wickramathunge", "pwick@asd.com", "student123", Role.STUDENT);
            User sadisha =  new User("sadisha","nimsara", "sadisha@asd.com", "teacher123", Role.TEACHER);
            User sahan =  new User("sahan","ambilipitiya", "sahan@asd.com", "admint123", Role.MANAGEMENT);

            List<User> users = Stream.of(pubudu, sadisha, sahan)
                    .collect(Collectors.toList());
            userRepository.saveAll(users);
        };

    }
}
