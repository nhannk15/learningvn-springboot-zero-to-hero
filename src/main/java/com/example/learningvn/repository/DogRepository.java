package com.example.learningvn.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningvn.model.entity.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    List<Dog> findByNameContainingIgnoreCase(String name);
    List<Dog> findByColorContainingIgnoreCase(String color);
}
