package com.store.app.controller;

import com.store.app.dto.CartDto;
import com.store.app.model.response.CartResponseModel;
import com.store.app.service.CartService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartController {
    @Autowired
    CartService cartService;
    //TODO do not allow to public id to be returned as HTTP response for objects inside cart

    @GetMapping
    @RequestMapping(path = "users/{id}/cart")
    public CartResponseModel getCurrentCart(@PathVariable("id") String userId) {
        CartResponseModel returnValue = new CartResponseModel();

        CartDto cartDto = cartService.getCartCurrentOnPublicUserId(userId);
        BeanUtils.copyProperties(cartDto, returnValue);

        return returnValue;
    }
}