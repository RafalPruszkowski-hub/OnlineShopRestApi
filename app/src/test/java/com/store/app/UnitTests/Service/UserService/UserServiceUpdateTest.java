package com.store.app.UnitTests.Service.UserService;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.RoleEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.CreatingUserErrorException;
import com.store.app.exception.user.UserAlreadyExistException;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.mapper.UserMapper;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import com.store.app.serviceImpl.UUIDGenerator;
import com.store.app.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(UserServiceImpl.class)
public class UserServiceUpdateTest {

    @Autowired
    private UserService userService;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    UUIDGenerator uuidGenerator;

    private Integer userId;
    private String publicUserId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String telephone;
    private String email;
    private String encryptedPassword;
    private List<OrderEntity> orders;
    private List<CartEntity> cart;
    private Collection<RoleEntity> roles;
    private String password;
    private UserDto userDto;
    private UserEntity userEntity;


    private void init(){
        this.encryptedPassword = "encryptedPassword";
        this.userId = 1;
        this.publicUserId = "publicUserId";
        this.firstName = "firstName";
        this.lastName = "lastName";
        this.address = "address";
        this.city = "city";
        this.telephone = "telephone";
        this.email = "email";
        this.password = "password";
        this.userDto = new UserDto(userId, publicUserId, firstName, lastName, address, city, telephone, email, password, encryptedPassword);
        this.userEntity = new UserEntity(userDto);
    }

    @Test
    public void updateUser_basic() {
        init();
        String newFirstName = "newFirstName";
        String newLastName = "newLastName";
        String newAddress = "newAddress";
        String newCity = "newCity";
        String newEmail = "newEmail";
        String newTelephone = "newTelephone";

        userDto.setFirstName(newFirstName);
        userDto.setLastName(newLastName);
        userDto.setAddress(newAddress);
        userDto.setCity(newCity);
        userDto.setEmail(newEmail);
        userDto.setTelephone(newTelephone);

        UserEntity newUserEntity = new UserEntity(userDto);

        when(userRepository.findByEmail(email)).thenReturn(userEntity);
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(newUserEntity);


        UserDto result = userService.update(userDto,email);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getPublicUserId(), publicUserId);
        assertEquals(result.getFirstName(), newFirstName);
        assertEquals(result.getLastName(), newLastName);
        assertEquals(result.getAddress(), newAddress);
        assertEquals(result.getCity(), newCity);
        assertEquals(result.getTelephone(), newTelephone);
        assertEquals(result.getEncryptedPassword(), encryptedPassword);
        assertEquals(result.getPassword(), null);
    }

    @Test()
    public void updateUser_UserNotFoundException() {
        init();
        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(UserNotFoundException.class, () -> {userService.update(userDto,email);});
    }
}
