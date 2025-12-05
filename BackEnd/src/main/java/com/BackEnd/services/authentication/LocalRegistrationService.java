package com.BackEnd.services.authentication;

import com.BackEnd.DTO.authentication.LocalRegisterData;
import com.BackEnd.exceptions.UserAlreadyExistsException;
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
public class LocalRegistrationService {

    private static final Logger logger = LoggerFactory.getLogger(LocalRegistrationService.class);
private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationRepository authenticationRepository;
    private static final Provider PROVIDER = Provider.LOCAL;
    public LocalRegistrationService(UserRepository userRepository,
                       AuthenticationRepository authenticationRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.authenticationRepository = authenticationRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional
    public Authentication register(LocalRegisterData data) {
        ensureEmailIsUnique(data.getEmail());

        User user = new User();
        user.setName(data.getUsername());
        userRepository.save(user);

        Authentication auth = new Authentication();
        auth.setProvider(PROVIDER);
        auth.setEmail(data.getEmail());
        auth.setPasswordHash(bCryptPasswordEncoder.encode(data.getPassword()));
        auth.setUser(user);

        return authenticationRepository.save(auth);
    }




    private void ensureEmailIsUnique(String email) {
        if (authenticationRepository.findByEmail(email) != null) {
            throw new UserAlreadyExistsException("User already exists with this email");
        }
    }

}