package com.tarunkumar.practice.Controller;



import com.tarunkumar.practice.Dto.RegisterRequestDTO;
import com.tarunkumar.practice.Service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequestDTO dto) {
        return service.register(dto);
    }
}