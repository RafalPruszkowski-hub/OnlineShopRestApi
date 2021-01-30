package com.store.app.service;

import com.store.app.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto create(String email);

    OrderDto get(String email, String publicOrderId);

    List<OrderDto> getList(String email, int page, int limit);
}
