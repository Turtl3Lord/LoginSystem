package com.BackEnd.controllers;

import com.BackEnd.models.User;
import com.BackEnd.repository.UserRepository;
import com.BackEnd.services.jwt.JwtTokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserCotroller {


    @Autowired
    UserRepository userRepository;

    JwtTokenService jwtTokenService;

    public UserCotroller(JwtTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }

    private User user;

    @GetMapping("/profile")
    public User getUserProfile( @RequestHeader("Authorization") String authHeader) {
        String token = authHeader.replace("Bearer ", "").trim();
        String userId = jwtTokenService.getSubjectFromToken(token);
        // Here you would typically validate the token and extract user information
        // For simplicity, let's assume the token is the user's email
        user = userRepository.findById(userId).orElse(null);
        if (user==null){
            throw new RuntimeException("User not found");
        }
        return user;
    }



}
