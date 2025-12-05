package com.BackEnd.services.authentication;

import com.BackEnd.DTO.authentication.LocalLoginData;
import com.BackEnd.exceptions.InvalidCredentialsException;
import com.BackEnd.models.Authentication;
import com.BackEnd.repository.AuthenticationRepository;
import com.BackEnd.repository.UserRepository;
import com.BackEnd.services.JwtTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LocalLoginService {

    private final AuthenticationRepository authenticationRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenService jwtTokenService;


    public LocalLoginService(UserRepository userRepository,
                             AuthenticationRepository authenticationRepository,
                             BCryptPasswordEncoder encoder,
                             JwtTokenService jwtTokenService) {
        this.authenticationRepository = authenticationRepository;
        this.encoder = encoder;
        this.jwtTokenService = jwtTokenService;
    }


    public String authenticate(LocalLoginData loginData) { 



        Authentication auth = authenticationRepository.findByEmail(
                loginData.getEmail());

        if (auth == null) {
            throw new InvalidCredentialsException("User not found");
        }

        if (!encoder.matches(loginData.getPassword(), auth.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

return jwtTokenService.generateToken(auth.getUser().getId());
    }
}
