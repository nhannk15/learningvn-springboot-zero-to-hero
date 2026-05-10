package com.example.learningvn.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;

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
    public Page<DogDTO> getAllDogs(Pageable pageable) {
        log.debug("SERVICE: fetching all dogs");
        Page<Dog> dogs = repository.findAll(pageable);
        log.debug("SERVICE: successfully fetched all dogs");
        Page<DogDTO> dtoPage = dogs.map(mapper::toDTO);
        return dtoPage;
    }

    @Override
    public Page<DogDTO> findDogsByName(String name, Pageable pageable) {
        log.debug("SERVICE: fetching all dogs with name: {}", name);
        Page<Dog> dogs = repository.findByNameContainingIgnoreCase(name, pageable);
        log.debug("SERVICE: successfully fetched all dogs with name: {}", name);
        return dogs.map(mapper::toDTO);
    }

    @Override
    public Page<DogDTO> findDogsByColor(String color, Pageable pageable) {
        log.debug("SERVICE: fetching all dogs with color: {}", color);
        Page<Dog> dogs = repository.findByColorContainingIgnoreCase(color, pageable);
        log.debug("SERVICE: successfully fetched all dogs with color: {}", color);
        return dogs.map(mapper::toDTO);
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
