package com.BackEnd.controllers;


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



    public AuthController(AuthService authService, JwtTokenService jwtTokenService) {
        this.authService = authService;
        this.jwtTokenService = jwtTokenService;
    }

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody AuthRequest request) {
    System.out.println("Received signin request: " + request);
        try {
            logger.info("Login attempt for email: {}", request.getEmail());
            logger.info("Provider: {}", request.getProvider());

            var result = authService.authenticate(request);

            logger.info("Login successful for: {}", request.getEmail());
            return ResponseEntity.ok(Map.of(
                    "success", true,
                    "message", "Login successful",
                    "token", jwtTokenService.generateToken(result.getUser())
            ));

        } catch (Exception e) {
            logger.error("Login error for email: {}", request.getEmail(), e);
            return ResponseEntity.status(500)
                    .body(Map.of(
                            "success", false,
                            "message", "Internal server error: " + e.getMessage()
                    ));
        }
    }

    @PostMapping("/signup")
    public String register(@RequestBody AuthRequest request) {
        authService.register(request);
        return "User Registered Successfully";
    }
}
