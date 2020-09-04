package com.store.app.controller;

import com.store.app.dto.CartItemDto;
import com.store.app.dto.CartItemDtoReturnCreating;
import com.store.app.model.request.CartItemDetailsRequest;
import com.store.app.model.response.CartItemResponseModel;
import com.store.app.service.CartItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartItemController {
    @Autowired
    CartItemService cartItemService;


    @PostMapping
    @RequestMapping(path="/users/{publicUserId}/product/{publicProductId}")
    public CartItemResponseModel createCartItem(@PathVariable("publicUserId") String publicUserId,
                                                @PathVariable("publicProductId") String publicProductId,
                                                @RequestBody CartItemDetailsRequest cartItemDetailsRequest){
        CartItemResponseModel returnValue = new CartItemResponseModel();
        CartItemDto cartItemDto = new CartItemDto();
        BeanUtils.copyProperties(cartItemDetailsRequest,cartItemDto);

        CartItemDtoReturnCreating storedCartItem = cartItemService.createCartItem(publicUserId,publicProductId, cartItemDto);
        BeanUtils.copyProperties(storedCartItem,returnValue);

        return returnValue;
    }
}
