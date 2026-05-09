package com.example.learningvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody User user) {
        log.info("REST request creating a new user");
        UserDTO dto = service.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @GetMapping
    public ResponseEntity<?> findAllUsers() {
        log.info("REST request fetching all users");
        List<UserDTO> dtoList = service.getAllUsers();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUsersById(@PathVariable Long id) {
        log.info("REST request finding user with id: {}", id);
        UserDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/username")
    public ResponseEntity<?> findUsersByUsername(@RequestParam(name = "username") String name) {
        log.info("REST request finding user with name: {}", name);
        List<UserDTO> dtoList = service.getUserByUsername(name);
        return ResponseEntity.ok().body(dtoList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateUser(
        @PathVariable Long id, 
        @Valid @RequestBody UserDTO dto) {
        log.info("REST request updating user with id: {}", id);
        UserDTO result = service.updateUser(id, dto);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("REST request deleting user with id: {}", id);
        service.deleteUser(id);
        return ResponseEntity.ok().body(null);
    }
}
