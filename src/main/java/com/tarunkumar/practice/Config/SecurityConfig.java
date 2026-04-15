package com.tarunkumar.practice.Config;

import com.tarunkumar.practice.Utility.JwtFilter;
import org.springframework.context.annotation.*;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Public
                        .requestMatchers("/auth/**").permitAll()

                        // ADMIN only
                        .requestMatchers(HttpMethod.POST, "/v1/asset/create").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/v1/asset/delete/**").hasRole("ADMIN")

                        // ALL asset APIs (ADMIN + USER)
                        .requestMatchers("/v1/asset/**").hasAnyRole("ADMIN", "USER")

                        // fallback
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter,
                        UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }}