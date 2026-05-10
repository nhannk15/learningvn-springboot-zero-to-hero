package com.example.learningvn.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.learningvn.model.dto.DogDTO;

public interface DogService {
    DogDTO createDog(DogDTO dto);
    Page<DogDTO> getAllDogs(Pageable pageable);
    Page<DogDTO> findDogsByName(String name, Pageable pageable);
    Page<DogDTO> findDogsByColor(String color, Pageable pageable);
    DogDTO findById(Long id);
    DogDTO updateDog(Long id, DogDTO dogDetails);
    void deleteDog(Long id);
}
