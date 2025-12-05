package com.BackEnd.services.authentication;

import com.BackEnd.DTO.request.AuthRequest;
import com.BackEnd.exceptions.InvalidCredentialsException;
import com.BackEnd.models.Authentication;
import com.BackEnd.repository.AuthenticationRepository;
import com.BackEnd.repository.UserRepository;
import com.BackEnd.services.JwtTokenService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class LocalLoginService {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private final BCryptPasswordEncoder encoder;
    private final JwtTokenService jwtTokenService;


    public LocalLoginService(UserRepository userRepository,
                             AuthenticationRepository authenticationRepository,
                             BCryptPasswordEncoder encoder,
                             JwtTokenService jwtTokenService) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.encoder = encoder;
        this.jwtTokenService = jwtTokenService;
    }


    public String authenticate(AuthRequest req) {

        String token = null;


        Authentication auth = authenticationRepository.findByEmailAndProvider(
                req.getEmail(), req.getProvider());

        if (auth == null) {
            throw new InvalidCredentialsException("User not found");
        }

        if (!encoder.matches(req.getPassword(), auth.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

return jwtTokenService.generateToken(auth.getUser().getId());
    }
}
