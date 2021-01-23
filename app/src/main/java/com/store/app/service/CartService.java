package com.store.app.service;

import com.store.app.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDto create(String publicUserId);

    CartDto getOnPublicUserId(String publicUserId);

    CartDto getOnUserEmail(String email);

    void updateTotalPrice(String publicUserId, double price);

    void saveForOrder(int cartId, int orderId);
}
