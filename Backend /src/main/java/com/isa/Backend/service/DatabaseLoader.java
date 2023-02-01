package com.isa.Backend.service;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
@Component
public class DatabaseLoader {

    @Autowired
    private UserRepository userRepository;



    @Bean
    public CommandLineRunner initializeDatabase(){

        return args -> {
            Users pubudu =  new Users("pubudu","Wickramathunge", "pwick@asd.com", "student123", Role.STUDENT);
            Users sadisha =  new Users("sadisha","nimsara", "sadisha@asd.com", "teacher123", Role.TEACHER);
            Users sahan =  new Users("sahan","ambilipitiya", "sahan@asd.com", "admint123", Role.MANAGEMENT);

            List<Users> users = Stream.of(pubudu, sadisha, sahan)
                    .collect(Collectors.toList());
            userRepository.saveAll(users);
        };

    }
}
