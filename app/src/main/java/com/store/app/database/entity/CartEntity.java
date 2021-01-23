package com.store.app.database.entity;


import com.store.app.dto.CartDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity(name = "carts")
public class CartEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cartId;
    @Column
    @NotEmpty
    private String publicCartId;
    @Column
    private double totalPrice = 0;
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CartItemEntity> cartItems;
    @ManyToOne
    @JoinColumn(name = "userId")
    private UserEntity user;
    @OneToOne(fetch = FetchType.EAGER,
            cascade = { CascadeType.REFRESH})

    @JoinColumn(name = "order_id")
    private OrderEntity orderEntity;


    public CartEntity(UserEntity user) {
        this.user = user;
    }

    public CartEntity() {
    }

    public CartEntity(CartDto cartDto) {
        BeanUtils.copyProperties(cartDto,this);
    }
}
