package com.store.app.dto;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = -4133332222091890797L;
    private Integer orderId;
    private String publicOrderId;
    private CartDto cart;
    private UserDto user;

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

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }
}
