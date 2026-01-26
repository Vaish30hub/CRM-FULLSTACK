package com.example.crm.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.crm.dto.LoginRequest;
import com.example.crm.dto.RegisterRequest;
import com.example.crm.model.User;
import com.example.crm.repository.UserRepository;
import com.example.crm.security.JwtUtil;

@CrossOrigin(
    origins = {
        "http://localhost:3000",
        "http://10.247.129.3:3000"
    }
)
@RestController
@RequestMapping("/api")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    // ================= REGISTER =================
    @PostMapping("/register")
    public Map<String, String> register(@RequestBody RegisterRequest request) {

        Map<String, String> response = new HashMap<>();

        if (userRepository.findByEmail(request.email).isPresent()) {
            response.put("message", "Email already registered");
            return response;
        }

        User user = new User();
        user.setFullName(request.fullName);
        user.setEmail(request.email);
        user.setPassword(passwordEncoder.encode(request.password));
        user.setRole(request.role);

        userRepository.save(user);

        response.put("message", "User registered successfully");
        return response;
    }

    // ================= LOGIN =================
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User user = userRepository.findByEmail(request.email)
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        Map<String, String> response = new HashMap<>();
        response.put("token", token);

        return response;
    }
}