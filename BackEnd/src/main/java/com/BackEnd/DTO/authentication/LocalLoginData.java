package com.BackEnd.DTO.authentication;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LocalLoginData {

        @NotBlank(message = "Email cannot be empty.")
        @Email(message = "Invalid email format.")
        private final String email;

        @NotBlank(message = "Password cannot be empty.")
        @Size(min = 8, message = "Password must be at least 8 characters long.")
        private final String password;

        public LocalLoginData( String email, String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }


