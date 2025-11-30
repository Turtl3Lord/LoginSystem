package com.BackEnd.services;

import com.BackEnd.exceptions.InvalidCredentialsException;
import com.BackEnd.exceptions.RegistrationException;
import com.BackEnd.exceptions.UserAlreadyExistsException;
import com.BackEnd.exceptions.ValidationException;
import com.BackEnd.requests.AuthRequest;
import com.BackEnd.models.Authentication;
import com.BackEnd.models.Provider;
import com.BackEnd.models.User;
import com.BackEnd.repository.AuthenticationRepository;
import com.BackEnd.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthService.class);

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

    @Transactional
    public Authentication register(AuthRequest req) {
        logger.info("Starting registration for email: {}", req.getEmail());

        // Validações
        validateRegistrationRequest(req);

        // Verifica se usuário já existe
        if (authenticationRepository.findByEmail(req.getEmail()) != null) {
            logger.warn("Registration failed: email already exists - {}", req.getEmail());
            throw new UserAlreadyExistsException("User already exists with this email");
        }

        try {
            // Cria usuário
            User user = new User();
            user.setName(req.getName());
            user = userRepository.save(user);

            // Cria autenticação
            Authentication auth = new Authentication();
            auth.setProvider(req.getProvider());
            auth.setProviderUserId(req.getProviderUserId());
            auth.setEmail(req.getEmail());
            auth.setUser(user);

            // Hash da senha para LOCAL
            if (req.getProvider() == Provider.LOCAL) {
                auth.setPasswordHash(encoder.encode(req.getPassword()));
            }

            auth = authenticationRepository.save(auth);
            logger.info("Registration successful for email: {}", req.getEmail());

            return auth;

        } catch (Exception e) {
            logger.error("Error during registration for email: {}", req.getEmail(), e);
            throw new RegistrationException("Error during registration: " + e.getMessage(), e);
        }
    }

    public Authentication authenticate(AuthRequest req) {
        logger.info("Authentication attempt for email: {}", req.getEmail());

        Authentication auth;

        if (req.getProvider() == Provider.LOCAL) {
            auth = authenticationRepository.findByEmailAndProvider(
                    req.getEmail(), req.getProvider());

            if (auth == null) {
                logger.warn("Authentication failed: user not found - {}", req.getEmail());
                throw new InvalidCredentialsException("Invalid email or password");
            }

            if (!encoder.matches(req.getPassword(), auth.getPasswordHash())) {
                logger.warn("Authentication failed: invalid password - {}", req.getEmail());
                throw new InvalidCredentialsException("Invalid email or password");
            }

        } else {
            auth = authenticationRepository.findByProviderUserIdAndProvider(
                    req.getProviderUserId(), req.getProvider());

            if (auth == null) {
                logger.warn("Authentication failed: provider user not found - {}", req.getProviderUserId());
                throw new InvalidCredentialsException("Invalid credentials");
            }
        }

        logger.info("Authentication successful for email: {}", req.getEmail());
        return auth;
    }

    private void validateRegistrationRequest(AuthRequest req) {
        if (req.getEmail() == null || req.getEmail().trim().isEmpty()) {
            throw new ValidationException("Email cannot be empty");
        }

        if (req.getName() == null || req.getName().trim().isEmpty()) {
            throw new ValidationException("Name cannot be empty");
        }

        if (req.getProvider() == Provider.LOCAL) {
            if (req.getPassword() == null || req.getPassword().isEmpty()) {
                throw new ValidationException("Password cannot be empty for local registration");
            }

            if (req.getPassword().length() < 6) {
                throw new ValidationException("Password must be at least 6 characters long");
            }
        }
    }
}