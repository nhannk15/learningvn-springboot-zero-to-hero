package com.example.learningvn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.learningvn.exception.DogNotFoundException;
import com.example.learningvn.mapper.DogMapper;
import com.example.learningvn.model.dto.DogDTO;
import com.example.learningvn.model.entity.Dog;
import com.example.learningvn.repository.DogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class DogServiceImpl implements DogService {

    private final DogRepository repository;
    private final DogMapper mapper;

    @Autowired
    public DogServiceImpl(DogRepository repository, DogMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public DogDTO createDog(DogDTO dto) {
        log.debug("SERVICE: creating a dog: {}", dto.getName());
        Dog dog = mapper.toEntity(dto);
        Dog createdDog = repository.save(dog);
        log.debug("SERVICE: successfully created a dog: {}", createdDog.getId());
        return mapper.toDTO(createdDog);
    }

    @Override
    public List<DogDTO> getAllDogs() {
        log.debug("SERVICE: fetching all dogs");
        List<Dog> dogs = repository.findAll();
        log.debug("SERVICE: successfully fetched all dogs");
        return mapper.toDTOList(dogs);
    }

    @Override
    public List<DogDTO> findDogsByName(String name) {
        log.debug("SERVICE: fetching all dogs with name: {}", name);
        List<Dog> dogs = repository.findByNameContainingIgnoreCase(name);
        log.debug("SERVICE: successfully fetched all dogs with name: {}", name);
        return mapper.toDTOList(dogs);
    }

    @Override
    public List<DogDTO> findDogsByColor(String color) {
        log.debug("SERVICE: fetching all dogs with color: {}", color);
        List<Dog> dogs = repository.findByColorContainingIgnoreCase(color);
        log.debug("SERVICE: successfully fetched all dogs with color: {}", color);
        return mapper.toDTOList(dogs);
    }

    @Override
    public DogDTO findById(Long id) {
        log.debug("SERVICE: finding a dog by id: {}", id);
        Dog dog = repository.findById(id)
                .orElseThrow(() -> new DogNotFoundException("Dog not found with id: " + id));
        log.debug("SERVICE: Dog found with id: {}", id);
        return mapper.toDTO(dog);
    }

    @Override
    public DogDTO updateDog(Long id, DogDTO dogDetails) {
        log.debug("SERVICE: finding a dog by id: {}", id);
        Dog dog = repository.findById(id)
                .orElseThrow(() -> new DogNotFoundException("Dog not found with id: " + id));
        log.debug("SERVICE: Dog found with id: {}", id);
        mapper.updateDogFromDto(dogDetails, dog);
        Dog updatedDog = repository.save(dog);
        log.debug("SERVICE: successfully updated a dog: {}", id);
        return mapper.toDTO(updatedDog);
    }

    @Override
    public void deleteDog(Long id) {
        log.debug("SERVICE: finding a dog by id: {}", id);
        Dog dog = repository.findById(id)
                .orElseThrow(() -> new DogNotFoundException("Dog not found with id: " + id));
        log.debug("SERVICE: Dog found with id: {}", id);
        repository.delete(dog);
        log.debug("SERVICE: successfully deleted a dog: {}", id);
    }

}
