package com.store.app.dto;

import com.store.app.database.entity.OrderEntity;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class OrderDto implements Serializable {
    private static final long serialVersionUID = -4133332222091890797L;
    private Integer orderId;
    private String publicOrderId;
    private CartDto cart;
    private UserDto user;

    public OrderDto(){

    }

    public OrderDto(OrderEntity orderEntity){
        BeanUtils.copyProperties(orderEntity,this);
        this.cart = new CartDto(orderEntity.getCart());
        this.user = new UserDto(orderEntity.getUser());
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
