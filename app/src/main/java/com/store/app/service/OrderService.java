package com.store.app.service;

import com.store.app.dto.OrderDto;
import com.store.app.model.response.OrderResponseModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    OrderDto create(String publicUserId);

    OrderDto get(String publicUserId);

    List<OrderDto> getList(String publicUserId, int page, int limit);
}
