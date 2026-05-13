package com.example.learningvn.model.dto;

import java.util.Collection;

import com.example.learningvn.model.entity.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserDTO {

    @Size(min = 5, max = 100, message = "Username must be betweem 5 - 100 characters")
    private String username;

    @Email(message = "Email wrong format")
    @NotBlank
    private String email;

    private Collection<Role> roles;

    private boolean enable;

    public UserDTO() {
    }

    public UserDTO(@Size(min = 5, max = 100, message = "Username must be betweem 5 - 100 characters") String username,
            @Email(message = "Email wrong format") @NotBlank String email, Collection<Role> roles, boolean enable) {
        this.username = username;
        this.email = email;
        this.roles = roles;
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

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    

}
