package com.store.app.dto;

import com.store.app.model.response.ProductResponseModel;

import java.io.Serializable;

public class CartItemDtoReturnCreating implements Serializable {
    private static final long serialVersionUID = -4121766793091890797L;
    private Integer cartItemId;
    private String publicCartItemId;
    private int quantity;
    private ProductResponseModel product;
    private double productsPrice;

    public Integer getCartItemId() {
        return cartItemId;
    }

    public void setCartItemId(Integer cartItemId) {
        this.cartItemId = cartItemId;
    }

    public String getPublicCartItemId() {
        return publicCartItemId;
    }

    public void setPublicCartItemId(String publicCartItemId) {
        this.publicCartItemId = publicCartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductResponseModel getProduct() {
        return product;
    }

    public void setProduct(ProductResponseModel product) {
        this.product = product;
    }

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }
}
