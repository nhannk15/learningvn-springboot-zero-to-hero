package com.example.learningvn.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningvn.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //--- Query Methods.
    List<User> findByUsernameContaining(String username);
    User findByEmail(String email);

    Optional<User> findByUsername(String username);
}
