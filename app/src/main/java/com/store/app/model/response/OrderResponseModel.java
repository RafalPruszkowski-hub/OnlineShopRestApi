package com.store.app.model.response;

import com.store.app.dto.CartDto;
import com.store.app.dto.UserDto;
import org.springframework.beans.BeanUtils;

public class OrderResponseModel {
    private String publicOrderId;
    private CartResponseModel cart;
    private UserResponseModel user;

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

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }

    public void setCart(CartDto cartDto) {
        CartResponseModel tmp = new CartResponseModel();
        BeanUtils.copyProperties(cartDto, tmp);
        this.cart = tmp;
    }

    public void setUser(UserDto userDto) {
        UserResponseModel tmp = new UserResponseModel();
        BeanUtils.copyProperties(userDto, tmp);
        this.user = tmp;
    }

}
