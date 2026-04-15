package com.tarunkumar.practice;


import com.tarunkumar.practice.Enum.Role;
import com.tarunkumar.practice.Entity.User;
import com.tarunkumar.practice.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final UserRepository repo;
    private final BCryptPasswordEncoder encoder;

    @Bean
    CommandLineRunner initUsers() {
        return args -> {

            if (repo.findByUsername("admin").isEmpty()) {
                repo.save(User.builder()
                        .username("admin")
                        .password(encoder.encode("admin123"))
                        .role(Role.ADMIN)
                        .build());
            }

        };
    }
}
