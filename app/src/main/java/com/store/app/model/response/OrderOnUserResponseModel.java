package com.store.app.model.response;

import com.store.app.dto.CartDto;
import org.springframework.beans.BeanUtils;

public class OrderOnUserResponseModel {
    private String publicOrderId;
    private CartResponseModel cart;

    public String getPublicOrderId() {
        return publicOrderId;
    }

    public void setPublicOrderId(String publicOrderId) {
        this.publicOrderId = publicOrderId;
    }

    public CartResponseModel getCart() {
        return cart;
    }

    public void setCart(CartResponseModel cart) {
        this.cart = cart;
    }


    public void setCart(CartDto cartDto) {
        CartResponseModel tmp = new CartResponseModel();
        BeanUtils.copyProperties(cartDto, tmp);
        this.cart = tmp;
    }

}
