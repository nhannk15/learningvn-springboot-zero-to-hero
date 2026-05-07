package com.example.learningvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.StudentNotFoundException;
import com.example.learningvn.model.entity.Student;
import com.example.learningvn.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService {
    private StudentRepository studentRepository;

    public StudentService() {
    }

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        log.info("Finding all students");
        return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        log.info("Finding student with id {}", id);
        if (id == null) {
            log.warn("The provided student ID was null");
            throw new IllegalArgumentException("ID can't be left null");
        }
        return studentRepository.findById(id).orElseThrow(() -> {
            log.error("Student not found with id: {}", id);
            return new StudentNotFoundException("Student not found with id: " + id);
        });
    }

    public Student addStudent(Student newStudent) {
        log.info("Adding new student: {}", newStudent.getName());
        return studentRepository.save(newStudent);
    }

    public Student updateStudent(Long id, Student student) {
        log.info("Updating student with id: {}", id);
        Student targetStudent = studentRepository.findById(id).get();
        targetStudent.setAge(student.getAge());
        targetStudent.setEmail(student.getEmail());
        targetStudent.setGpa(student.getGpa());
        targetStudent.setMajor(student.getMajor());
        targetStudent.setName(student.getName());
        studentRepository.save(targetStudent);
        return targetStudent;
    }

    public Student deleteStudent(Long id) {
        log.debug("Deleting student with id: {}", id);
        Student student = studentRepository.findById(id).get();
        studentRepository.delete(student);
        return student;
    }
}
