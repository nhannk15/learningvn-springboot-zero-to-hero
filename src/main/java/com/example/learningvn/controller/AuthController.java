package com.example.learningvn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.JwtResponse;
import com.example.learningvn.model.dto.LoginRequest;
import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.service.AuthService;
import com.example.learningvn.util.JwtTokenUtil;

@RestController
@RequestMapping("/auth/api")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService detailsService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Validated @RequestBody User userDetails) {
        UserDTO response = authService.registerUser(userDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<?> doLogin(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
    
        UserDetails userDetails = detailsService.loadUserByUsername(loginRequest.getUsername());

        String token = jwtTokenUtil.generateToken(loginRequest.getUsername());

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
    }
}
