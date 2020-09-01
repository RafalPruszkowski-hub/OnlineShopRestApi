package com.store.app.dto;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = -4133332222091890797L;
    private Integer orderId;
    private String publicOrderId;
    private CartDto cart;
    private UserDto user;

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getPublicOrderId() {
        return publicOrderId;
    }

    public void setPublicOrderId(String publicOrderId) {
        this.publicOrderId = publicOrderId;
    }

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cartDto) {
        cart = cartDto;
    }

    public void setCart(CartEntity cartEntity) {
        CartDto tmp = new CartDto();
        BeanUtils.copyProperties(cartEntity, tmp);
        this.cart = tmp;
    }

    public void setUser(UserEntity userEntity) {
        UserDto tmp = new UserDto();
        BeanUtils.copyProperties(userEntity, tmp);
        this.user = tmp;
    }
}
