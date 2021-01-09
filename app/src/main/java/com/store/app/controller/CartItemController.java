package com.store.app.controller;

import com.store.app.dto.CartItemDto;
import com.store.app.dto.CartItemDtoReturnCreating;
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
        if (!principal.getName().equals(userService.getUser(publicUserId).getEmail())) throw new RuntimeException("Wrong authorization header is provided");
        CartItemResponseModel returnValue = new CartItemResponseModel();
        CartItemDto cartItemDto = new CartItemDto();
        BeanUtils.copyProperties(cartItemDetailsRequest, cartItemDto);

        CartItemDtoReturnCreating storedCartItem = cartItemService.createCartItem(publicUserId, publicProductId, cartItemDto);
        BeanUtils.copyProperties(storedCartItem, returnValue);

        return returnValue;
    }
}
