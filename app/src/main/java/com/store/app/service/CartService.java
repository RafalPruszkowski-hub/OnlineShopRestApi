package com.store.app.service;

import com.store.app.dto.CartDto;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    CartDto createCart(String publicUserId);

    CartDto getCartCurrentOnPublicUserId(String publicUserId);

    CartDto createCart(int userId);

    CartDto updateTotalPrice(String publicUserId);

    CartDto updateTotalPrice(String publicUserId, double newProductPrice);

    CartDto getCart(String cartId);
}
