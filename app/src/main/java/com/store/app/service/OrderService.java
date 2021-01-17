package com.store.app.service;

import com.store.app.dto.OrderDto;
import com.store.app.model.response.OrderResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto createOrder(String publicUserId);

    OrderDto getOrder(String publicUserId);

    List<OrderDto> getOrders(String publicUserId, int page, int limit);
}
