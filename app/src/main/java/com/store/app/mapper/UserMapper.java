package com.store.app.mapper;

import com.store.app.database.entity.UserEntity;
import com.store.app.dto.UserDto;
import com.store.app.model.response.UserResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring",nullValuePropertyMappingStrategy= NullValuePropertyMappingStrategy.IGNORE)
public abstract class UserMapper {
    public abstract UserDto toDto(UserEntity userEntity);
    public abstract UserEntity toEntity(UserDto userDto);
    public abstract UserResponseModel toResponse(UserDto userDto);
    public abstract UserEntity applyChanges(@MappingTarget() UserEntity userEntity, UserDto userDto);
}
