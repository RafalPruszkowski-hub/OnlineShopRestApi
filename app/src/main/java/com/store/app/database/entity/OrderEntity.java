package com.store.app.database.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity(name = "orders")

public class OrderEntity {
        @Id
        @Column
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer orderId;

        @Column
        @NotEmpty
        private String publicOrderId;

        @OneToOne(fetch = FetchType.EAGER,
                    cascade = {CascadeType.MERGE, CascadeType.REFRESH})
        @JoinColumn(name = "cart_id")
        private CartEntity cart;

        @ManyToOne(fetch = FetchType.EAGER,
                cascade = {CascadeType.MERGE, CascadeType.REFRESH})
        @JoinColumn(name = "user_Id")
        private UserEntity user;

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

    public CartEntity getCart() {
        return cart;
    }

    public void setCart(CartEntity cart) {
        this.cart = cart;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
