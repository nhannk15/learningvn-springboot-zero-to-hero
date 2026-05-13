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

    private String role;

    private boolean enable;

    public UserDTO() {
    }

    public UserDTO(String username, String email, String role, boolean enable) {
        this.username = username;
        this.email = email;
        this.role = role;
        this.enable = enable;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "UserDTO [username=" + username + ", email=" + email + "]";
    }

}
