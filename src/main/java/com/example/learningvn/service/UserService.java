package com.example.learningvn.service;

import java.util.List;

import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;

public interface UserService {
    UserDTO createUser(User userDetails);
    UserDTO findById(Long id);
    List<UserDTO> getAllUsers();
    UserDTO updateUser(Long id, UserDTO userDetails);
    void deleteUser(Long id);
    List<UserDTO> getUserByUsername(String username);
}
