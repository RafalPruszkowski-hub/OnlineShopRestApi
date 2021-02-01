package com.store.app.UnitTests.Mapper;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.RoleEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.dto.UserDto;
import com.store.app.model.response.UserResponseModel;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import com.store.app.mapper.UserMapper;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserMapperTest {
    private UserMapper mapper = Mappers.getMapper(UserMapper.class);

    private int userId = 1;
    private String publicUserId = "publicUserId";
    private String firstName = "firstName";
    private String lastName = "lastName";
    private String address = "address";
    private String city = "city";
    private String telephone = "telephone";
    private String email = "email";
    private String password = "password";
    private String encryptedPassword = "encryptedPassword";
    private ArrayList<OrderEntity> orderList = new ArrayList<>();
    private ArrayList<CartEntity> cartList= new ArrayList<>();
    private ArrayList<RoleEntity> roleList= new ArrayList<>();

    @Test
    public void DtoToEntity() {
        UserDto dto = new UserDto(userId,publicUserId,firstName,lastName,address,city,telephone,email,password,encryptedPassword);

        UserEntity entity = mapper.toEntity(dto);

        assertEquals(dto.getUserId(), entity.getUserId());
        assertEquals(dto.getPublicUserId(),entity.getPublicUserId());
        assertEquals(dto.getFirstName(), entity.getFirstName());
        assertEquals(dto.getLastName(), entity.getLastName());
        assertEquals(dto.getAddress(), entity.getAddress());
        assertEquals(dto.getCity(), entity.getCity());
        assertEquals(dto.getTelephone(), entity.getTelephone());
        assertEquals(dto.getEmail(), entity.getEmail());
        assertEquals(dto.getEncryptedPassword(),entity.getEncryptedPassword());
    }

    @Test
    public void EntityToDto() {
        UserEntity entity = new UserEntity(userId,publicUserId,firstName,lastName,address,city,telephone,email,encryptedPassword,orderList,cartList,roleList);

        UserDto dto = mapper.toDto(entity);

        assertEquals(entity.getUserId(), dto.getUserId());
        assertEquals(entity.getPublicUserId(),dto.getPublicUserId());
        assertEquals(entity.getFirstName(), dto.getFirstName());
        assertEquals(entity.getLastName(), dto.getLastName());
        assertEquals(entity.getAddress(), dto.getAddress());
        assertEquals(entity.getCity(), dto.getCity());
        assertEquals(entity.getTelephone(), dto.getTelephone());
        assertEquals(entity.getEmail(), dto.getEmail());
        assertEquals(entity.getEncryptedPassword(),dto.getEncryptedPassword());
        assertNull(dto.getPassword());
    }

    @Test
    public void DtoToResponse(){
        UserDto dto = new UserDto(userId,publicUserId,firstName,lastName,address,city,telephone,email,password,encryptedPassword);

        UserResponseModel responseModel = mapper.toResponse(dto);

        assertEquals(dto.getPublicUserId(),responseModel.getPublicUserId());
        assertEquals(dto.getFirstName(), responseModel.getFirstName());
        assertEquals(dto.getLastName(), responseModel.getLastName());
        assertEquals(dto.getAddress(), responseModel.getAddress());
        assertEquals(dto.getCity(), responseModel.getCity());
        assertEquals(dto.getTelephone(), responseModel.getTelephone());
        assertEquals(dto.getEmail(), responseModel.getEmail());
    }

    @Test
    public void ApplyChangesDtoToEntity_basic(){
        int userIdNew = 2;
        String publicUserIdNew = "publicUserIdNew";
        String firstNameNew = "firstNameNew";
        String lastNameNew = "lastNameNew";
        String addressNew = "addressNew";
        String cityNew = "cityNew";
        String telephoneNew = "telephoneNew";
        String emailNew = "emailNew";
        String passwordNew = "passwordNew";
        String encryptedPasswordNew = "encryptedPasswordNew";
        UserEntity entity = new UserEntity(userId,publicUserId,firstName,lastName,address,city,telephone,email,encryptedPassword,orderList,cartList,roleList);
        UserDto dto = new UserDto(userIdNew,publicUserIdNew,firstNameNew,lastNameNew,addressNew,cityNew,telephoneNew,emailNew,passwordNew,encryptedPasswordNew);

        UserEntity result = mapper.applyChanges(entity,dto);

        assertEquals(dto.getUserId(), result.getUserId());
        assertEquals(dto.getPublicUserId(),result.getPublicUserId());
        assertEquals(dto.getFirstName(), result.getFirstName());
        assertEquals(dto.getLastName(), result.getLastName());
        assertEquals(dto.getAddress(), result.getAddress());
        assertEquals(dto.getCity(), result.getCity());
        assertEquals(dto.getTelephone(), result.getTelephone());
        assertEquals(dto.getEmail(), result.getEmail());
        assertEquals(dto.getEncryptedPassword(),result.getEncryptedPassword());
    }

    @Test
    public void ApplyChangesDtoToEntity_onNullValuesInsideDto(){
        UserEntity entity = new UserEntity(userId,publicUserId,firstName,lastName,address,city,telephone,email,encryptedPassword,orderList,cartList,roleList);
        UserDto dto = new UserDto(1,null,null,null,null,null,null,null,null,null);

        UserEntity result = mapper.applyChanges(entity,dto);

        assertEquals(userId, result.getUserId());
        assertEquals(publicUserId,result.getPublicUserId());
        assertEquals(firstName, result.getFirstName());
        assertEquals(lastName, result.getLastName());
        assertEquals(address, result.getAddress());
        assertEquals(city, result.getCity());
        assertEquals(telephone, result.getTelephone());
        assertEquals(email, result.getEmail());
        assertEquals(encryptedPassword,result.getEncryptedPassword());
    }
}