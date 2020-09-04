package com.store.app.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "cart_items")
public class CartItemEntity implements Serializable {
    private static final long serialVersionUID = 2L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartItemId;

    @Column
    private String publicCartItemId;

    @Column
    private int quantity;

    @Column
    private double productsPrice = 0;

    @ManyToOne//with cart
    @JoinColumn(name = "cartId")//primary key of cart
    @JsonIgnore
    private CartEntity cart;

    @ManyToOne//with products
    @JoinColumn(name = "cartProductId")
    private ProductEntity product;


    public CartItemEntity() {
    }

    public CartItemEntity(CartEntity cart, ProductEntity product) {
        this.cart = cart;
        this.product = product;
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

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }
}
