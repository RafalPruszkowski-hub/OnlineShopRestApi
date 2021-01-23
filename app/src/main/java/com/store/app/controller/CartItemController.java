package com.store.app.controller;

import com.store.app.dto.CartItemDto;
import com.store.app.model.request.CartItemDetailsRequest;
import com.store.app.model.response.CartItemResponseModel;
import com.store.app.service.CartItemService;
import com.store.app.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
public class CartItemController {
    @Autowired
    CartItemService cartItemService;

    @Autowired
    UserService userService;

    @PostMapping
    @RequestMapping(path = "/users/{publicUserId}/product/{publicProductId}")
    public CartItemResponseModel createCartItem(@PathVariable("publicUserId") String publicUserId,
                                                @PathVariable("publicProductId") String publicProductId,
                                                @RequestBody CartItemDetailsRequest cartItemDetailsRequest,
                                                Principal principal) {
        if (!principal.getName().equals(userService.get(publicUserId).getEmail())) throw new RuntimeException("Wrong authorization header is provided");

        CartItemDto cartItemDto = new CartItemDto();
        BeanUtils.copyProperties(cartItemDetailsRequest, cartItemDto);

        CartItemDto storedCartItem = cartItemService.create(publicUserId, publicProductId, cartItemDto);
        CartItemResponseModel returnValue = new CartItemResponseModel(storedCartItem);

        return returnValue;
    }
}
