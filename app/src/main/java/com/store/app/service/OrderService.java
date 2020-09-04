package com.store.app.service;

import com.store.app.dto.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    OrderDto createOrder(String publicUserId);
}
