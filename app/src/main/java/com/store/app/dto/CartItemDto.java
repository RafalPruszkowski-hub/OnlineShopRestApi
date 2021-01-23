package com.store.app.dto;

import com.store.app.database.entity.CartItemEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
public class CartItemDto implements Serializable {
    private static final long serialVersionUID = -4131766793091890797L;
    private Integer cartItemId;
    private String publicCartItemId;
    private int quantity;
    private CartDto cart;
    private ProductDto product;
    private double productsPrice;

    public CartItemDto() {}

    public CartItemDto(CartItemEntity cartItemEntity) {
        BeanUtils.copyProperties(cartItemEntity,this);
        product = new ProductDto(cartItemEntity.getProduct());
    }
}
