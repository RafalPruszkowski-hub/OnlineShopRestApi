package com.store.app.controller;

import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import com.store.app.dto.UserDto;
import com.store.app.model.request.UserDetailsRequestModel;
import com.store.app.model.response.CartItemResponseModel;
import com.store.app.model.response.CartResponseModel;
import com.store.app.model.response.ProductResponseModel;
import com.store.app.model.response.UserResponseModel;
import com.store.app.service.CartService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @GetMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel getUser(@PathVariable("id") String userId, Principal principal) {
        UserResponseModel returnValue = new UserResponseModel();
        UserDto userDto = userService.get(userId);

        //TODO implement custom error message
        if (!userDto.getEmail().equals(principal.getName())) return null;

        BeanUtils.copyProperties(userDto, returnValue);
        return returnValue;
    }

    @PostMapping
    public UserResponseModel createUser(@RequestBody UserDetailsRequestModel userDetailsRequestModel) {
        UserResponseModel returnValue = new UserResponseModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);

        UserDto createdUser = userService.create(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return returnValue;
    }

    @GetMapping
    public ArrayList<UserResponseModel> getUsers(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ) {
        ArrayList<UserResponseModel> returnValue = new ArrayList<>();

        List<UserDto> users = userService.getList(page, limit);

        for (UserDto userDto : users) {
            UserResponseModel userResponseModel = new UserResponseModel();
            BeanUtils.copyProperties(userDto, userResponseModel);
            returnValue.add(userResponseModel);
        }
        return returnValue;
    }

    @PutMapping(path = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponseModel updateUser(@PathVariable("id") String id, @RequestBody UserDetailsRequestModel userDetailsRequestModel, Principal principal) {
        UserResponseModel returnValue = new UserResponseModel();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetailsRequestModel, userDto);

        //TODO implement custom error message
        if (!userDto.getEmail().equals(principal.getName())) return null;

        UserDto updateUser = userService.update(userDto, id);
        BeanUtils.copyProperties(updateUser, returnValue);

        return returnValue;
    }

    @GetMapping
    @RequestMapping(path = "/{id}/cart")
    public CartResponseModel getCurrentCart(@PathVariable("id") String userId, Principal principal) {
        CartResponseModel returnValue = new CartResponseModel();

        CartDto cartDto = cartService.getOnPublicUserId(userId);

        //TODO implement custom error message
        if (!cartDto.getUser().getEmail().equals(principal.getName())) return null;

        BeanUtils.copyProperties(cartDto, returnValue);

        //Creating ResponseModels for products and cartItems to hide database ID
        List<CartItemResponseModel> returnItems = new ArrayList<>();
        for (CartItemDto cartItemDto : cartDto.getCartItems()) {
            CartItemResponseModel cartItemResponseModel = new CartItemResponseModel();
            BeanUtils.copyProperties(cartItemDto, cartItemResponseModel);

            ProductResponseModel productResponseModel = new ProductResponseModel();
            BeanUtils.copyProperties(cartItemDto.getProduct(), productResponseModel);

            cartItemResponseModel.setProduct(productResponseModel);
            returnItems.add(cartItemResponseModel);
        }
        returnValue.setCartItems(returnItems);
        return returnValue;
    }
}