package com.example.learningvn.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningvn.model.entity.Dog;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    Page<Dog> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Dog> findByColorContainingIgnoreCase(String color, Pageable pageable);
}
