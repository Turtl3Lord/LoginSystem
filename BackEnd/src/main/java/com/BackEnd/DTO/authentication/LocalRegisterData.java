package com.BackEnd.DTO.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocalRegisterData {




        @NotBlank(message = "Username cannot be empty.")
        @Size(min = 3, max = 32, message = "Username must be between 3 and 32 characters.")
        private final String username;

        @NotBlank(message = "Email cannot be empty.")
        @Email(message = "Invalid email format.")
        private final String email;

        @NotBlank(message = "Password cannot be empty.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        private final String password;

        public LocalRegisterData(String username, String email, String password) {
            this.username = username;
            this.email = email;
            this.password = password;
        }

        public String getUsername() { return username; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }
