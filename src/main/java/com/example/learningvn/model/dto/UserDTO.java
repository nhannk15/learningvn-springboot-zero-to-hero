package com.example.learningvn.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Size(min = 5, max = 100, message = "Username must be betweem 5 - 100 characters")
    private String username;

    @Email(message = "Email wrong format")
    @NotBlank
    private String email;

    public UserDTO() {
    }
    public UserDTO(String username, String email) {
        this.username = username;
        this.email = email;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    @Override
    public String toString() {
        return "UserDTO [username=" + username + ", email=" + email + "]";
    }

}
