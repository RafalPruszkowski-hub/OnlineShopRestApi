package com.store.app.service;

import com.store.app.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto create(ProductDto productDto);

    ProductDto get(String id);

    ProductDto update(ProductDto req, String id);

    List<ProductDto> getList(int page, int limit);

    ProductDto updateProductStock(int quantity, String publicProductId);
}