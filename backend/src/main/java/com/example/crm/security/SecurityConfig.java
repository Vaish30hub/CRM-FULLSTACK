package com.example.crm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .cors() 
            .and()
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth

                
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                
                .requestMatchers(
                        "/api/login",
                        "/api/register",
                        "/swagger-ui/**",
                        "/v3/api-docs/**"
                ).permitAll()

                .requestMatchers("/api/**").permitAll()

                .anyRequest().authenticated()
            );

        return http.build();
    }
}