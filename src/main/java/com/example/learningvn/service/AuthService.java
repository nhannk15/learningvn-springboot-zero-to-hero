package com.example.learningvn.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.learningvn.mapper.UserMapper;
import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.Role;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.RoleRepository;
import com.example.learningvn.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper mapper;

    public UserDTO registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByName("ROLE_USER")
        .orElseGet(() -> {
            Role newRole = new Role("ROLE_USER");
            return roleRepository.save(newRole);
        });
        user.setRoles(Collections.singletonList(userRole));

        User createdUser = userRepository.save(user);
        return mapper.toDTO(createdUser);
    }
}
