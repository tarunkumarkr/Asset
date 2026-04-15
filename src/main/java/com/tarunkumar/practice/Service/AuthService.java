package com.tarunkumar.practice.Service;


import com.tarunkumar.practice.Dto.LoginRequestDTO;
import com.tarunkumar.practice.Enum.Role;
import com.tarunkumar.practice.Dto.RegisterRequestDTO;
import com.tarunkumar.practice.Entity.User;
import com.tarunkumar.practice.Repo.UserRepository;
import com.tarunkumar.practice.Utility.JwtUtil;
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
            return "User already exists";
        }
        User user = User.builder()
                .username(dto.getUsername())
                .password(encoder.encode(dto.getPassword()))
                .role(Role.valueOf(dto.getRole().toUpperCase()))
                .build();
        userRepo.save(user);

        return "User registered successfully";
    }
    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public String login(LoginRequestDTO dto) {

        User user = userRepo.findByUsername(dto.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return jwtUtil.generateToken(user.getUsername());
    }
}