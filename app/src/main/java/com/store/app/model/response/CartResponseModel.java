package com.store.app.model.response;

import java.util.List;

public class CartResponseModel {
    private String publicCartId;
    private double totalPrice;
    private List<CartItemResponseModel> cartItems;


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


    public List<CartItemResponseModel> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemResponseModel> cartItems) {
        this.cartItems = cartItems;
    }

    @Override
    public String toString() {
        return "CartResponseModel{" +
                "publicCartId='" + publicCartId + '\'' +
                ", totalPrice=" + totalPrice +
                ", cartItems=" + cartItems +
                '}';
    }
}
