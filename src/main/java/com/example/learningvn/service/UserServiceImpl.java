package com.example.learningvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.UserEmailDuplicatedException;
import com.example.learningvn.exception.UserNotFoundException;
import com.example.learningvn.mapper.UserMapper;
import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;
import com.example.learningvn.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public UserDTO createUser(User userDetails) {
        log.debug("SERVICE: adding user: {}", userDetails.getUsername());
        if (repository.findByEmail(userDetails.getEmail()) != null) {
            log.warn("SERVICE: email duplicated");
            throw new UserEmailDuplicatedException("Duplicated email: " + userDetails.getEmail());
        }
        User createdUser = repository.save(userDetails);
        log.debug("SERVICE: successfully created user: {}", createdUser.getUsername());
        return mapper.toDTO(createdUser);
    }

    @Override
    public UserDTO findById(Long id) {
        log.debug("SERVICE: finding user: {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not foudn with id: " + id));
        log.debug("SERVICE: user found: {}", id);
        return mapper.toDTO(user);
    }

    @Override
    public List<UserDTO> getAllUsers() {
        log.debug("SERVICE: fetching all users");
        List<User> users = repository.findAll();
        log.debug("Success fully fetch {} users", users.size());
        return mapper.toDTOList(users);
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDetails) {
        log.debug("SERVICE: updating user with id: {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        if (repository.findByEmail(userDetails.getEmail()) != null) {
            log.warn("SERVICE: email duplicated");
            throw new UserEmailDuplicatedException("Duplicated email: " + userDetails.getEmail());
        }
        log.debug("SERVICE: user found: {}", id);
        mapper.updateUserFromDto(userDetails, user);
        User updatedUser = repository.save(user);
        log.debug("SERVICE: successfully updated user: {}", id);
        return mapper.toDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        log.debug("SERVICE: deleting user with id: {}", id);
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        log.debug("SERVICE: user found: {}", id);
        repository.delete(user);    
        log.debug("SERVICE: successfully deleted user: {}", id);
    }

    @Override
    public List<UserDTO> getUserByUsername(String username) {
        log.debug("SERVICE: fetching all users with name: {}", username);
        List<User> users = repository.findByUsernameContaining(username);
        log.debug("Success fully fetch {} users", users.size());
        return mapper.toDTOList(users);
    }

}
