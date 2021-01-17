package com.store.app.model.response;

import com.store.app.dto.CartItemDto;
import org.springframework.beans.BeanUtils;

public class CartItemResponseModel {
    private String publicCartItemId;
    private int quantity;
    private double productsPrice;
    private ProductResponseModel product;

    public CartItemResponseModel() {
    }
    public CartItemResponseModel(CartItemDto cartItemDto) {
        BeanUtils.copyProperties(cartItemDto,this);
        product = new ProductResponseModel(cartItemDto.getProduct());
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

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public ProductResponseModel getProduct() {
        return product;
    }

    public void setProduct(ProductResponseModel product) {
        this.product = product;
    }
}
