package com.store.app.service;

import com.store.app.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(String id);

    ProductDto updateProduct(ProductDto req, String id);

    List<ProductDto> getProducts(int page, int limit);

    ProductDto updateProductStock(int quantity, String publicProductId);
}