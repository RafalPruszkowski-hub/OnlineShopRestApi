package com.store.app.dto;

import com.store.app.database.entity.UserEntity;
import com.store.app.model.request.UserDetailsRequestModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class UserDto implements Serializable {
    private static final long serialVersionUID = -4131766793091890797L;
    private int userId;
    private String publicUserId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String email;
    private String password;
    private String encryptedPassword;

    public UserDto() {
    }

    public UserDto(UserEntity userEntity) {
        BeanUtils.copyProperties(userEntity, this);
    }

    public UserDto(UserDetailsRequestModel userDetailsRequestModel) {
        BeanUtils.copyProperties(userDetailsRequestModel, this);
    }
}

