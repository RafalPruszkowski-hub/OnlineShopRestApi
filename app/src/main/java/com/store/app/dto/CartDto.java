package com.store.app.dto;

import com.store.app.database.entity.CartEntity;
import com.store.app.database.entity.CartItemEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CartDto implements Serializable {
    private static final long serialVersionUID = 1331231L;
    private Integer cartId;
    private String publicCartId;
    private double totalPrice;
    private UserDto user;
    private List<CartItemDto> cartItems;
    private OrderDto orderDto;

    public CartDto(){

    }
    public CartDto(CartEntity cartEntity){
        BeanUtils.copyProperties(cartEntity,this);
        cartItems = new ArrayList<>();
        for(CartItemEntity cartItemEntity : cartEntity.getCartItems()){
            CartItemDto cartItemDto = new CartItemDto(cartItemEntity);
            cartItems.add(cartItemDto);
        }
        user = new UserDto(cartEntity.getUser());
    }
}
