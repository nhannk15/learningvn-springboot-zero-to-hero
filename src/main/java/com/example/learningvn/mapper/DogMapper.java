package com.example.learningvn.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.learningvn.model.dto.DogDTO;
import com.example.learningvn.model.entity.Dog;

@Mapper(componentModel = "spring")

public interface DogMapper {
    
    DogDTO toDTO(Dog dog);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateBorn", ignore = true)
    Dog toEntity(DogDTO dog);

    List<DogDTO> toDTOList(List<Dog> entites);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "dateBorn", ignore = true)
    void updateDogFromDto(DogDTO dto, @MappingTarget Dog entity);
}
