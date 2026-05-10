package com.example.learningvn.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.learningvn.model.dto.DogDTO;

public interface DogService {
    DogDTO createDog(DogDTO dto);
    Page<DogDTO> getAllDogs(int page, int size, String sortBy, String direction);
    Page<DogDTO> findDogsByName(String name, int page, int size);
    Page<DogDTO> findDogsByColor(String color, int page, int size);
    DogDTO findById(Long id);
    DogDTO updateDog(Long id, DogDTO dogDetails);
    void deleteDog(Long id);
}
