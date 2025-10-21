package com.sergiosilva.aa5ev01.auth;

import com.sergiosilva.aa5ev01.auth.dto.LoginRequest;
import com.sergiosilva.aa5ev01.auth.dto.UserRegisterRequest;
import com.sergiosilva.aa5ev01.auth.dto.UserResponse;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody UserRegisterRequest request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {
        String message = service.login(request.getUsernameOrEmail(), request.getPassword());
        Map<String, String> body = new HashMap<>();
        body.put("message", message);
        return body;
    }
}