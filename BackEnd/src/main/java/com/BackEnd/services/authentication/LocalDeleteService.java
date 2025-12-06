package com.BackEnd.services.authentication;

import com.BackEnd.DTO.authentication.DeleteAccountData;
import com.BackEnd.exceptions.InvalidCredentialsException;
import com.BackEnd.models.Authentication;
import com.BackEnd.models.User;
import com.BackEnd.repository.AuthenticationRepository;
import com.BackEnd.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LocalDeleteService {

    private final AuthenticationRepository authenticationRepository;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public LocalDeleteService(AuthenticationRepository authenticationRepository,
                              UserRepository userRepository,
                              BCryptPasswordEncoder encoder) {
        this.authenticationRepository = authenticationRepository;
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @Transactional
    public void deleteAccount(DeleteAccountData deleteData) {
        Authentication auth = authenticationRepository.findByEmail(deleteData.getEmail());

        if (auth == null) {
            throw new InvalidCredentialsException("User not found");
        }

        if (!encoder.matches(deleteData.getPassword(), auth.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        User user = auth.getUser();
        authenticationRepository.delete(auth);
        userRepository.delete(user);
    }
}