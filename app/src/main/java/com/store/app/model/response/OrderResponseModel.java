package com.store.app.model.response;

import com.store.app.dto.OrderDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class OrderResponseModel {
    private String publicOrderId;
    private UserResponseModel user;
    private CartResponseModel cart;

    public OrderResponseModel() {
    }

    public OrderResponseModel(OrderDto orderDto) {
        BeanUtils.copyProperties(orderDto, this);
        user = new UserResponseModel(orderDto.getUser());
        cart = new CartResponseModel(orderDto.getCart());
    }
}
