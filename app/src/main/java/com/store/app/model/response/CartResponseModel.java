package com.store.app.model.response;

import com.store.app.dto.CartDto;
import com.store.app.dto.CartItemDto;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

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
