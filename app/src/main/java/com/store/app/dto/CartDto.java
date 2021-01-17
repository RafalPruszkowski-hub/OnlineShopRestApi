package com.store.app.dto;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartDto implements Serializable {
    private static final long serialVersionUID = 1331231L;
    private Integer cartId;
    private String publicCartId;
    private double totalPrice;
    private UserDto user;
    private List<CartItemDto> cartItems;
    private OrderDto orderDto;

    public CartDto(){

    }
    public CartDto(CartEntity cartEntity){
        BeanUtils.copyProperties(cartEntity,this);
        cartItems = new ArrayList<>();
        for(CartItemEntity cartItemEntity : cartEntity.getCartItems()){
            CartItemDto cartItemDto = new CartItemDto(cartItemEntity);
            cartItems.add(cartItemDto);
        }
        user = new UserDto(cartEntity.getUser());
    }

    public Integer getCartId() {
        return cartId;
    }

    public void setCartId(Integer cartId) {
        this.cartId = cartId;
    }

    public String getPublicCartId() {
        return publicCartId;
    }

    public void setPublicCartId(String publicCartId) {
        this.publicCartId = publicCartId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<CartItemDto> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemDto> cartItems) {
        this.cartItems = cartItems;
    }

    public OrderDto getOrderDto() {
        return orderDto;
    }

    public void setOrderDto(OrderDto orderDto) {
        this.orderDto = orderDto;
    }
}
