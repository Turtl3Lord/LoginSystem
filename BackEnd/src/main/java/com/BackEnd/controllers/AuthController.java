package com.BackEnd.controllers;

import com.BackEnd.DTO.authentication.LocalRegisterData;
import com.BackEnd.models.Authentication;
import com.BackEnd.DTO.request.AuthRequest;
import com.BackEnd.services.AuthService;
import com.BackEnd.services.JwtTokenService;
import com.BackEnd.services.authentication.LocalRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth/local")
public class AuthController {

    private final AuthService authService;
    private final LocalRegistrationService localRegistrationService;
    private final JwtTokenService jwtTokenService;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public AuthController(AuthService authService, JwtTokenService jwtTokenService, LocalRegistrationService localRegistrationService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
        this.localRegistrationService = localRegistrationService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthRequest request) {
        Authentication result = authService.authenticate(request);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "token", jwtTokenService.generateToken(result.getUser().getId())
        ));
    }

    @PostMapping("/signup/local")
    public ResponseEntity<?> signup(@RequestBody LocalRegisterData localRegisterData) {

       Authentication result =  localRegistrationService.register(localRegisterData);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "User registered successfully",
                "token", jwtTokenService.generateToken(result.getUser().getId())));
    }
}