package com.store.app.model.response;

import com.store.app.dto.CartItemDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
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
}
