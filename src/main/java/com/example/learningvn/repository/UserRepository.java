package com.example.learningvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningvn.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
