package com.store.app.controller;

import com.store.app.dto.CartItemDto;
import com.store.app.model.request.CartItemDetailsRequest;
import com.store.app.model.response.CartItemResponseModel;
import com.store.app.service.CartItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CartItemController {
    @Autowired
    CartItemService cartItemService;


    @PostMapping(path = "/users/{userId}/products/{productId}")
    public CartItemResponseModel createCartItem(@PathVariable("productId") String productId, @PathVariable("userId") String userId, @RequestBody CartItemDetailsRequest cartItemDetailsRequest) {
        CartItemResponseModel returnValue = new CartItemResponseModel();

        CartItemDto cartItemDto = new CartItemDto();
        BeanUtils.copyProperties(cartItemDetailsRequest, cartItemDto);

        CartItemDto createdCartItem = cartItemService.createItem(cartItemDto, productId, userId);
        BeanUtils.copyProperties(createdCartItem, returnValue);
        returnValue.setProduct(createdCartItem.getProduct());

        return returnValue;
    }
}
