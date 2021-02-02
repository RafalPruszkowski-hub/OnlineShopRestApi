package com.store.app.mapper;

import com.store.app.database.entity.UserEntity;
import com.store.app.dto.UserDto;
import com.store.app.model.request.UserDetailsRequestModel;
import com.store.app.model.request.UserLoginRequestModel;
import com.store.app.model.response.UserResponseModel;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring")
public abstract class UserMapper {
    public abstract UserDto toDto(UserEntity userEntity);

    public abstract UserDto toDtoFromRequestDetails(UserDetailsRequestModel userDetailsRequestModel);

    public abstract UserDto toDtoFromRequestLogin(UserLoginRequestModel userLoginRequestModel);

    public abstract UserEntity toEntity(UserDto userDto);

    public abstract UserResponseModel toResponse(UserDto userDto);

    public abstract List<UserResponseModel> dtoToResponseList(List<UserDto> userDtoList);

    public abstract List<UserDto> entityToDtoList(List<UserEntity> userEntityList);

    //TODO make it not to copy nulls
    public abstract UserEntity applyChanges(@MappingTarget() UserEntity userEntity, UserDto userDto);
}
