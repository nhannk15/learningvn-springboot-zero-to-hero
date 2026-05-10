package com.example.learningvn.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.learningvn.model.dto.DogDTO;
import com.example.learningvn.service.DogService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/dogs")
public class DogController {

    private static final int PAGE_SIZE = 5;

    private final DogService service;

    public DogController(DogService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createDog(@RequestBody DogDTO dto) {
        log.info("REST request creating a dog");
        DogDTO dog = service.createDog(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dog);
    }

    @GetMapping("/{dogId}")
    public ResponseEntity<?> findDogById(@PathVariable Long dogId) {
        log.info("REST request finding a dog by id");
        DogDTO dog = service.findById(dogId);
        return ResponseEntity.status(HttpStatus.OK).body(dog);
    }

    @GetMapping
    public ResponseEntity<?> findAllDogs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        if (page < 0) {
            return ResponseEntity.badRequest().body("Page number must ≥ 0");
        }
        log.info("REST request finding all dogs");
        Page<DogDTO> allDogs = service.getAllDogs(page, PAGE_SIZE, sortBy, direction);
        return ResponseEntity.status(HttpStatus.OK).body(allDogs);
    }

    @GetMapping("/search")
    public ResponseEntity<?> findDogsByName(
        @RequestParam(name = "name") String name,
        @RequestParam(defaultValue = "0") int page) {
        if (page < 0) {
            return ResponseEntity.badRequest().body("Page number must ≥ 0");
        }
        log.info("REST request finding all dogs by name: {}", name);
        Page<DogDTO> allDogsByName = service.findDogsByName(name, page, PAGE_SIZE);
        return ResponseEntity.status(HttpStatus.OK).body(allDogsByName);
    }

    @GetMapping("/color")
    public ResponseEntity<?> findDogsByColor(
        @RequestParam(name = "color") String color,
        @RequestParam(defaultValue = "0") int page) {
        if (page < 0) {
            return ResponseEntity.badRequest().body("Page number must ≥ 0");
        }
        log.info("REST request finding all dogs by color: {}", color);
        Page<DogDTO> allDogsByColor = service.findDogsByColor(color, page, PAGE_SIZE);
        return ResponseEntity.status(HttpStatus.OK).body(allDogsByColor);
    }

    @PatchMapping("/{dogId}")
    public ResponseEntity<?> updateDog(@PathVariable Long dogId, @RequestBody DogDTO dto) {
        log.info("REST request updating a dog: {}", dogId);
        DogDTO dog = service.updateDog(dogId, dto);
        return ResponseEntity.status(HttpStatus.OK).body(dog);
    }

    @DeleteMapping("/{dogId}")
    public ResponseEntity<?> deleteDog(@PathVariable Long dogId) {
        log.info("REST request deleting a dog: {}", dogId);
        service.deleteDog(dogId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
