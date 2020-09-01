package com.store.app.service;

import com.store.app.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto createOrder(String publicCartId);

    List<OrderDto> getOrdersForUser(String publicUserId);
}
