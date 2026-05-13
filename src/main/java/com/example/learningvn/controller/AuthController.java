package com.example.learningvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.service.AuthService;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User userDetails) {
        UserDTO response = authService.registerUser(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
