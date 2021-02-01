package com.store.app.UnitTests.Service.UserService;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.OrderEntity;
import com.store.app.database.entity.RoleEntity;
import com.store.app.database.entity.UserEntity;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.UserDto;
import com.store.app.exception.user.UserNotFoundException;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import com.store.app.serviceImpl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(UserServiceImpl.class)
public class GetTest {

    @Autowired
    private UserService userService;

    @MockBean
    private CartService cartService;

    @MockBean
    private UserRepository userRepository;

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

    @Test
    public void getUser_basic() {
        userId = 1;
        publicUserId = "publicUserId";
        firstName = "firstName";
        lastName = "lastName";
        address = "address";
        city = "city";
        telephone = "telephone";
        email = "email";
        encryptedPassword = "encryptedPassword";
        orders = new ArrayList<OrderEntity>();
        cart = new ArrayList<CartEntity>();
        roles = new ArrayList<RoleEntity>();
        UserDto result = new UserDto(userId, publicUserId, firstName, lastName, address, city, telephone, email, null, encryptedPassword);

        when(userRepository.findByEmail(email)).thenReturn(new UserEntity(1, publicUserId, firstName, lastName, address, city, telephone, email, encryptedPassword, orders, cart, roles));
        UserDto userDto = userService.get(email);
        assertEquals(userDto.getUserId(), userId);
        assertEquals(userDto.getPublicUserId(), publicUserId);
        assertEquals(userDto.getFirstName(), firstName);
        assertEquals(userDto.getLastName(), lastName);
        assertEquals(userDto.getAddress(), address);
        assertEquals(userDto.getCity(), city);
        assertEquals(userDto.getTelephone(), telephone);
        assertEquals(userDto.getEncryptedPassword(), encryptedPassword);
        assertEquals(userDto.getPassword(), null);
    }

    @Test
    public void getUser_null() {
        email = "email";
        when(userRepository.findByEmail(email)).thenReturn(null);
        Exception exception = assertThrows(UserNotFoundException.class, () -> {
            userService.get(email);
        });
    }
}
