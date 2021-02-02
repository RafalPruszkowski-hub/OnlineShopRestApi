package com.store.app.controller;

import com.store.app.dto.CartDto;
import com.store.app.dto.UserDto;
import com.store.app.mapper.UserMapper;
import com.store.app.model.request.UserDetailsRequestModel;
import com.store.app.model.response.CartResponseModel;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping(consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel get(Principal principal) {
        UserDto userDto = userService.get(principal.getName());

        UserResponseModel returnValue = userMapper.toResponse(userDto);
        return returnValue;
    }

    @PostMapping(path = "/register")
    public UserResponseModel create(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserDto userDto = userMapper.toDtoFromRequestDetails(userDetailsRequestModel);

        UserDto createdUser = userService.create(userDto);

        UserResponseModel returnValue = userMapper.toResponse(createdUser);
        return returnValue;
    }


    @PutMapping(path = "/edit",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel update(@RequestBody UserDetailsRequestModel userDetailsRequestModel, Principal principal) {
        UserDto userDto = userMapper.toDtoFromRequestDetails(userDetailsRequestModel);
        UserDto updatedUser = userService.update(userDto, principal.getName());

        UserResponseModel returnValue = userMapper.toResponse(updatedUser);
        return returnValue;
    }


    //TODO MOVE FROM HERE
    @GetMapping
    @RequestMapping(path = "/cart")
    public CartResponseModel getCurrentCart(Principal principal) {
        CartDto cartDto = cartService.getOnUserEmail(principal.getName());

        CartResponseModel returnValue = new CartResponseModel(cartDto);
        return returnValue;
    }
}