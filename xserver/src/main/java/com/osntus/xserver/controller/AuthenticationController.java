package com.osntus.xserver.controller;

import com.osntus.xserver.dto.LoginRequest;
import com.osntus.xserver.dto.RegisterRequest;
import com.osntus.xserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("app/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    // register
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        return service.register(registerRequest);
    }

    // login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        return service.login(loginRequest);
    }

    // access token decode
    @GetMapping("/")
    public ResponseEntity<?> decode(@RequestHeader("Authorization") String token) {
        return service.decode(token);
    }
}
