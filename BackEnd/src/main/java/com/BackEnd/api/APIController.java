package com.BackEnd.api;

import com.BackEnd.models.User;
import com.BackEnd.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class APIController {
    private final UserRepository userRepository;

    public APIController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/user")
    public User receiveUser(@RequestBody User user) {
        userRepository.saveUser(user.getLogin(), user.getPassword());
        return user; // devolve o que recebeu só para teste
    }
}
