package com.example.learningvn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.learningvn.model.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    
}
