package com.store.app.dto;

import com.store.app.database.entity.OrderEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
public class OrderDto implements Serializable {
    private static final long serialVersionUID = -4133332222091890797L;
    private Integer orderId;
    private String publicOrderId;
    private CartDto cart;
    private UserDto user;

    public OrderDto(){

    }

    public OrderDto(OrderEntity orderEntity){
        BeanUtils.copyProperties(orderEntity,this);
        this.cart = new CartDto(orderEntity.getCart());
        this.user = new UserDto(orderEntity.getUser());
    }
}
