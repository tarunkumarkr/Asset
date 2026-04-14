package com.tarunkumar.practice.Service;


import com.tarunkumar.practice.Enum.Role;
import com.tarunkumar.practice.Dto.RegisterRequestDTO;
import com.tarunkumar.practice.Entity.User;
import com.tarunkumar.practice.Repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepo;
    private final BCryptPasswordEncoder encoder;

    public String register(RegisterRequestDTO dto) {

        if (userRepo.findByUsername(dto.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .role(Role.USER)
                .build();
        userRepo.save(user);

        return "User registered successfully";
    }
}