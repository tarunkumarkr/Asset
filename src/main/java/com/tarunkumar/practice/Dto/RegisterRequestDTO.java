package com.tarunkumar.practice.Dto;

import lombok.Data;

@Data
public class RegisterRequestDTO {
    private String username;
    private String password;
    private String role; // ADMIN / USER
}