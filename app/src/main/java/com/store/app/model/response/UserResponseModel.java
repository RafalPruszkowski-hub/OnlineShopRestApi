package com.store.app.model.response;

import com.store.app.dto.UserDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class UserResponseModel {
    private String publicUserId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String email;

    public UserResponseModel(){}

    public UserResponseModel(UserDto userDto) {
        BeanUtils.copyProperties(userDto,this);
    }
}
