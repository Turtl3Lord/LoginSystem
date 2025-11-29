package com.BackEnd.services;

import com.BackEnd.requests.AuthRequest;
import com.BackEnd.models.Authentication;
import com.BackEnd.models.Provider;
import com.BackEnd.models.User;
import com.BackEnd.repository.AuthenticationRepository;
import com.BackEnd.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private final BCryptPasswordEncoder encoder;

    public AuthService(UserRepository userRepository,
                       AuthenticationRepository authenticationRepository,
                       BCryptPasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.encoder = encoder;
    }

    public void register(AuthRequest req) {

        User user = new User();
        user.setName(req.getName());

        Authentication auth = new Authentication();

        auth.setProvider(req.getProvider());
        auth.setProviderUserId(req.getProviderUserId());
        auth.setEmail(req.getEmail());




        if (req.getProvider() == Provider.LOCAL) {
            if(req.getPassword() == null || req.getPassword().isEmpty()) {
                throw new RuntimeException("Password cannot be empty for local registration");
            }
            auth.setPasswordHash(encoder.encode(req.getPassword()));
            if(authenticationRepository.findByEmail(auth.getEmail()) != null) {
                throw new RuntimeException("User already exists with this email");
            }
        }
        try{
            user = userRepository.save(user);
            auth.setUser(user);
            authenticationRepository.save(auth);} catch (Exception e){
            throw new RuntimeException("Error during registration");
        }


    }

    public Authentication authenticate(AuthRequest req) {

        Authentication auth;

        if (req.getProvider() == Provider.LOCAL) {
            auth = authenticationRepository.findByEmailAndProvider(
                    req.getEmail(), req.getProvider());

            if (auth == null || !encoder.matches(req.getPassword(), auth.getPasswordHash()))
                throw new RuntimeException("Invalid credentials");
        } else {
            auth = authenticationRepository.findByProviderUserIdAndProvider(
                    req.getProviderUserId(), req.getProvider());

            if (auth == null)
                throw new RuntimeException("Invalid credentials");

        }
            return auth;
    }
}
