package com.store.app.UnitTests.Service.UserService;

import com.store.app.Util.UUIDGenerator;
import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.RoleEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.CartDto;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.CreatingUserErrorException;
import com.store.app.exception.user.UserAlreadyExistException;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import com.store.app.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@WebMvcTest(UserServiceImpl.class)
public class UserServiceCreateTest {

    @Autowired
    private UserService userService;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    private UUIDGenerator uuidGenerator;

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


    private void init() {
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
        this.orders = new ArrayList<OrderEntity>();
        this.cart = new ArrayList<CartEntity>();
        this.roles = new ArrayList<RoleEntity>();
        this.userDto = new UserDto(userId, publicUserId, firstName, lastName, address, city, telephone, email, password, encryptedPassword);
        this.userEntity = new UserEntity(userDto);
    }

    @Test
    public void createUser_basic() {
        init();
        userDto.setEncryptedPassword(null);
        userDto.setUserId(0);

        when(userRepository.findByEmail(email)).thenReturn(null);
        when(uuidGenerator.generate()).thenReturn(publicUserId);
        when(bCryptPasswordEncoder.encode(password)).thenReturn(encryptedPassword);
        when(userRepository.save(Mockito.any(UserEntity.class))).thenReturn(userEntity);
        when(cartService.create(anyString())).thenReturn(new CartDto());


        UserDto result = userService.create(userDto);
        assertEquals(result.getUserId(), 1);
        assertEquals(result.getPublicUserId(), publicUserId);
        assertEquals(result.getFirstName(), firstName);
        assertEquals(result.getLastName(), lastName);
        assertEquals(result.getAddress(), address);
        assertEquals(result.getCity(), city);
        assertEquals(result.getTelephone(), telephone);
        assertEquals(result.getEncryptedPassword(), encryptedPassword);
        assertEquals(result.getPassword(), null);
    }

    @Test()
    public void createUser_AlreadyExist() {
        init();
        when(userRepository.findByEmail(email)).thenReturn(new UserEntity());
        assertThrows(UserAlreadyExistException.class, () -> {
            userService.create(userDto);
        });
    }

    @Test
    public void createUser_creatingError() {
        init();
        when(userRepository.findByEmail(email)).thenReturn(null);
        assertThrows(CreatingUserErrorException.class, () -> {
            userService.create(userDto);
        });
    }
}
