package com.store.app.service;

import com.store.app.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {
    CartDto createCart(String publicUserId);

    CartDto getCartCurrentOnPublicUserId(String publicUserId);

    void updateTotalPrice(String publicUserId, double price);

    void saveCartOrder(String publicCartId, String publicOrderId);
}
