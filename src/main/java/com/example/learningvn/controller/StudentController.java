package com.example.learningvn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.entity.Student;
import com.example.learningvn.service.StudentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private StudentService studentService;

    public StudentController() {
    }

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        log.debug("Fetching all students");
        List<Student> students = studentService.findAll();

        log.info("Successfully fetched {} students", students.size());
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> findStudentById(@PathVariable Long id) {
        log.debug("Finding a student");
        Student student = studentService.getStudentById(id);
        
        log.info("Successfully fetched a student: {}", student.getName());
        return ResponseEntity.ok(student);
    }

    @PostMapping
    public ResponseEntity<Student> addNewStudent(@RequestBody Student student) {
        log.debug("Adding a student");
        Student result = studentService.addStudent(student);

        log.info("Successfully added a student: {}", result.getName());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
        log.debug("Updating a student");
        Student result = studentService.updateStudent(id, student);

        log.info("Successfully updated a student: {}", result.getName());
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        log.debug("Deleting a student");
        Student result = studentService.deleteStudent(id);

        log.info("Successfully delete a student: {}", result.getName());
        return ResponseEntity.ok(result);
    }

}
