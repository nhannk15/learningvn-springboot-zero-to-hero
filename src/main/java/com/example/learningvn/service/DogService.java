package com.example.learningvn.service;

import java.util.List;

import com.example.learningvn.model.dto.DogDTO;

public interface DogService {
    DogDTO createDog(DogDTO dto);
    List<DogDTO> getAllDogs();
    List<DogDTO> findDogsByName(String name);
    List<DogDTO> findDogsByColor(String color);
    DogDTO findById(Long id);
    DogDTO updateDog(Long id, DogDTO dogDetails);
    void deleteDog(Long id);
}
