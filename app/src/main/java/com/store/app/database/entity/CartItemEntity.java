package com.store.app.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
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
}
