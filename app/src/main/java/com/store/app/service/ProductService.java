package com.store.app.service;

import com.store.app.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto createProduct(ProductDto productDto);

    ProductDto getProduct(String id);

    ProductDto updateProduct(ProductDto req, String id);

    void deleteProduct(String productId);

    List<ProductDto> getProducts(int page, int limit);

    void updateProductAmount(int quantity, String publicProductId);
}
/*
    UserDto createUser(UserDto user);
    UserDto getUser(String id);
    UserDto updateUser(UserDto req, String id);
    void deleteUser(String userId);
    List<UserDto> getUsers(int page, int limit);
* */