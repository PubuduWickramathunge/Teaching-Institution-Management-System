package com.isa.Backend;

import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.model.Role;
import com.isa.Backend.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class DatabaseLoader {
    private final AuthenticationService authenticationService;

    @Bean
    public void loadSampleData() {
        authenticationService.register(
                new RegisterRequest(
                        "Pubudu",
                        "Wickramathunge",
                        "pwick@gmail.com",
                        "Zxcvbnm99",
                        Role.MANAGEMENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Kamal",
                        "Perera",
                        "kamal.perera@gmail.com",
                        "123456",
                        Role.STUDENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Nimal",
                        "Fernando",
                        "nimal.fernando@gmail.com",
                        "654321",
                        Role.STUDENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Saman",
                        "Jayasinghe",
                        "saman.jayasinghe@gmail.com",
                        "111111",
                        Role.STUDENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Bimal",
                        "Ranasinghe",
                        "Bimal.ranasinghe@gmail.com",
                        "222222",
                        Role.STUDENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Sumal",
                        "Rajapaksa",
                        "Sumal.rajapaksa@gmail.com",
                        "333333",
                        Role.STUDENT
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Kusum",
                        "Wickramasinghe",
                        "Kusum.wickramasinghe@gmail.com",
                        "444444",
                        Role.TEACHER
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Sunil",
                        "Silva",
                        "sunil.silva@gmail.com",
                        "555555",
                        Role.TEACHER
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Simal",
                        "Perera",
                        "Simal.perera@gmail.com",
                        "666666",
                        Role.TEACHER
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Pmw",
                        "Pmw",
                        "Pmw999@gmail.com",
                        "Pmw999@gmail.com",
                        Role.STUDENT
                )
        );
        authenticationService.register(
                new RegisterRequest(
                        "Pmw",
                        "Pmw",
                        "Pmw1999@gmail.com",
                        "Pmw1999@gmail.com",
                        Role.TEACHER
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Lakshan",
                        "Dissanayake",
                        "Lakshan.dissanayake@gmail.com",
                        "777777",
                        Role.TEACHER
                )
        );

        authenticationService.register(
                new RegisterRequest(
                        "Ranil",
                        "Bandara",
                        "Ranil.bandara@gmail.com",
                        "888888",
                        Role.TEACHER
                )
        );

    }
}
