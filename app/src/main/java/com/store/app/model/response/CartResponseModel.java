package com.store.app.model.response;

import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartResponseModel {
    private String publicCartId;
    private double totalPrice;
    private List<CartItemResponseModel> cartItems;

    public CartResponseModel() {
    }

    public CartResponseModel(CartDto cartDto) {
        BeanUtils.copyProperties(cartDto,this);
        cartItems = new ArrayList<>();
        for(CartItemDto cartItemDto : cartDto.getCartItems()){
            CartItemResponseModel cartItemResponseModel = new CartItemResponseModel(cartItemDto);
            cartItems.add(cartItemResponseModel);
        }
    }
}
