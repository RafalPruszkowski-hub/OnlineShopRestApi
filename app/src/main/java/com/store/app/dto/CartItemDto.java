package com.store.app.dto;

import com.store.app.database.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

public class CartItemDto implements Serializable {
    private static final long serialVersionUID = -4131766793091890797L;
    private Integer cartItemId;
    private String publicCartItemId;
    private int quantity;
    private CartDto cart;
    private ProductDto product;
    private double productsPrice;

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

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

    public CartDto getCart() {
        return cart;
    }

    public void setCart(CartDto cart) {
        this.cart = cart;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public void setProduct(ProductEntity product) {
        ProductDto tmp = new ProductDto();
        BeanUtils.copyProperties(product, tmp);
        this.product = tmp;
    }
}
