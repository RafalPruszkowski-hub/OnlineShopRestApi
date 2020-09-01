package com.store.app.service;

import com.store.app.dto.CartItemDto;
import org.springframework.stereotype.Service;

@Service
public interface CartItemService {
    CartItemDto createItem(CartItemDto cartItemDto, String publicProductId, String publicUserId);
}
