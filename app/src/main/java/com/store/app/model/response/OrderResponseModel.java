package com.store.app.model.response;

public class OrderResponseModel {
    private String publicOrderId;
    private UserResponseModel user;
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

    public UserResponseModel getUser() {
        return user;
    }

    public void setUser(UserResponseModel user) {
        this.user = user;
    }
}
