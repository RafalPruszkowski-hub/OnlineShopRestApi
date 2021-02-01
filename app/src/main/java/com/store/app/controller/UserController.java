package com.store.app.controller;

import com.store.app.dto.CartDto;
import com.store.app.dto.UserDto;
import com.store.app.model.request.UserDetailsRequestModel;
import com.store.app.model.response.CartResponseModel;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;


    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel get(Principal principal) {
        UserDto userDto = userService.get(principal.getName());

        UserResponseModel returnValue = new UserResponseModel(userDto);
        BeanUtils.copyProperties(userDto, returnValue);

        return returnValue;
    }

    @PostMapping(path = "/register")
    public UserResponseModel create(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserDto userDto = new UserDto(userDetailsRequestModel);
        UserDto createdUser = userService.create(userDto);
        UserResponseModel returnValue = new UserResponseModel(createdUser);

        return returnValue;
    }


    @PutMapping(path = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel update(@RequestBody UserDetailsRequestModel userDetailsRequestModel, Principal principal) {
        UserDto userDto = new UserDto(userDetailsRequestModel);

        UserDto updatedUser = userService.update(userDto, principal.getName());
        UserResponseModel returnValue = new UserResponseModel(updatedUser);

        return returnValue;
    }

    @GetMapping
    @RequestMapping(path = "/cart")
    public CartResponseModel getCurrentCart(Principal principal) {
        CartDto cartDto = cartService.getOnUserEmail(principal.getName());

        CartResponseModel returnValue = new CartResponseModel(cartDto);

        return returnValue;
    }
}