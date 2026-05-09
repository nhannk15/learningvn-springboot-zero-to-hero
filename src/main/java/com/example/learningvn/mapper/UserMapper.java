package com.example.learningvn.mapper;

import java.util.List;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.example.learningvn.model.dto.UserDTO;
import com.example.learningvn.model.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserDTO toDTO(User entity);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    User toEntity(UserDTO dto);

    List<UserDTO> toDTOList(List<User> entities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "dateCreated", ignore = true)
    @Mapping(target = "dateUpdated", ignore = true)
    void updateUserFromDto(UserDTO dto, @MappingTarget User entity);

}
