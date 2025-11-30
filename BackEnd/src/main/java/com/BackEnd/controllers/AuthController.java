package com.BackEnd.controllers;

import com.BackEnd.models.Authentication;
import com.BackEnd.requests.AuthRequest;
import com.BackEnd.services.AuthService;
import com.BackEnd.services.JwtTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final JwtTokenService jwtTokenService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, JwtTokenService jwtTokenService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthRequest request) {
        Authentication result = authService.authenticate(request);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "token", jwtTokenService.generateToken(result.getUser())
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequest request) {
       Authentication result =  authService.register(request);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "User registered successfully",
                "token", jwtTokenService.generateToken(result.getUser())));
    }
}