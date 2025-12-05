package com.BackEnd.controllers;

import com.BackEnd.DTO.authentication.LocalLoginData;
import com.BackEnd.DTO.authentication.LocalRegisterData;
import com.BackEnd.models.Authentication;
import com.BackEnd.services.JwtTokenService;
import com.BackEnd.services.authentication.LocalLoginService;
import com.BackEnd.services.authentication.LocalRegistrationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
@RequestMapping("/api/auth/local")
public class AuthController {

    private final LocalRegistrationService localRegistrationService;
    private final JwtTokenService jwtTokenService;
    private final LocalLoginService localLoginService;

    public AuthController( JwtTokenService jwtTokenService, LocalRegistrationService localRegistrationService, LocalLoginService localLoginService ) {
        this.jwtTokenService = jwtTokenService;
        this.localRegistrationService = localRegistrationService;
        this.localLoginService = localLoginService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody LocalLoginData loginData) {
        String token = localLoginService.authenticate(loginData);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "Login successful",
                "token", token
        ));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody LocalRegisterData localRegisterData) {
        System.out.println("Processing signup request for: " + localRegisterData.getName());

       Authentication result =  localRegistrationService.register(localRegisterData);

        return ResponseEntity.ok(Map.of(
                "success", true,
                "message", "User registered successfully",
                "token", jwtTokenService.generateToken(result.getUser().getId())));
    }
}