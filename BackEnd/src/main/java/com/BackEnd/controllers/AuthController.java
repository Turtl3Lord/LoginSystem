package com.BackEnd.controllers;

import com.BackEnd.DTO.authentication.DeleteAccountData;
import com.BackEnd.DTO.authentication.LocalLoginData;
import com.BackEnd.DTO.authentication.LocalRegisterData;
import com.BackEnd.models.Authentication;
import com.BackEnd.services.authentication.LocalDeleteService;
import com.BackEnd.services.authentication.LocalLoginService;
import com.BackEnd.services.authentication.LocalRegistrationService;
import com.BackEnd.services.jwt.JwtTokenService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth/local")
public class AuthController {


    private final LocalRegistrationService localRegistrationService;
    private final JwtTokenService jwtTokenService;
    private final LocalLoginService localLoginService;
    private final LocalDeleteService localDeleteService;

    public AuthController(JwtTokenService jwtTokenService,
                         LocalRegistrationService localRegistrationService,
                         LocalLoginService localLoginService,
                         LocalDeleteService localDeleteService) {
        this.jwtTokenService = jwtTokenService;
        this.localRegistrationService = localRegistrationService;
        this.localLoginService = localLoginService;
        this.localDeleteService = localDeleteService;
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signin(@Valid @RequestBody LocalLoginData loginData) {
        //retorna um token jwt
        String token = localLoginService.authenticate(loginData);

        return ResponseEntity.ok(createTokenResponse(token, null));
       
    }

    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> signup(@Valid @RequestBody LocalRegisterData localRegisterData) {

        Authentication result = localRegistrationService.register(localRegisterData);
        String token = jwtTokenService.generateToken(result.getUser().getId());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createTokenResponse(token, null));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteAccount(@Valid @RequestBody DeleteAccountData deleteData) {
        localDeleteService.deleteAccount(deleteData);

        return ResponseEntity.ok(createSuccessResponse("Account deleted successfully"));
    }
    

    private Map<String, Object> createSuccessResponse(String message) {
        return Map.of(
                "success", true,
                "message", message
        );
    }

    private Map<String, Object> createTokenResponse(String accessToken, Long expiresIn) {  
        if (expiresIn == null) {  
            // Sem expires_in por enquanto  
            return Map.of(  
                    "access_token", accessToken,  
                    "token_type", "Bearer"  
            );  
        }  
  
        return Map.of(  
                "access_token", accessToken,  
                "token_type", "Bearer",  
                "expires_in", expiresIn  
        );  
    }  

}