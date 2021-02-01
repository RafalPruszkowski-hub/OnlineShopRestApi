package com.store.app.UnitTests.Service.ProductService;

import com.store.app.Util.UUIDGenerator;
import com.store.app.database.entity.ProductEntity;
import com.store.app.database.repository.ProductRepository;
import com.store.app.database.repository.UserRepository;
import com.store.app.dto.ProductDto;
import com.store.app.exception.product.ProductNotFoundException;
import com.store.app.service.ProductService;
import com.store.app.service.UserService;
import com.store.app.serviceImpl.ProductServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@WebMvcTest(ProductServiceImpl.class)
public class GetTest {
    @Autowired
    private ProductService productService;
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductRepository productRepository;
    @MockBean
    private UUIDGenerator uuidGenerator;

    //TODO FIND WHY w/o userService and userRepository it's not working
    @MockBean
    private UserService userService;
    @MockBean
    private UserRepository userRepository;


    @Test
    public void getProduct_basic() {
        Integer productId = 1;
        String publicProductId = "publicProductId";
        String productName = "productName";
        String productBrand = "productBrand";
        String productModel = "productModel";
        String productDescription = "productDescription";
        int quantityOfStock = 10;
        double productPrice = 0.3;
        ProductEntity productEntity = new ProductEntity(productId, publicProductId, productName, productBrand, productModel, productDescription, quantityOfStock, productPrice, new ArrayList<>());

        when(uuidGenerator.generate()).thenReturn(publicProductId);
        when(productRepository.findByPublicProductId(publicProductId)).thenReturn(productEntity);

        ProductDto result = productService.get(publicProductId);
        assertEquals(result.getProductId(), productId);
        assertEquals(result.getPublicProductId(), publicProductId);
        assertEquals(result.getProductName(), productName);
        assertEquals(result.getProductBrand(), productBrand);
        assertEquals(result.getProductModel(), productModel);
        assertEquals(result.getProductDescription(), productDescription);
        assertEquals(result.getQuantityOfStock(), quantityOfStock);
        assertEquals(result.getProductPrice(), productPrice);
    }

    @Test
    public void getProduct_null() {
        String publicProductId = "publicProductId";
        when(productRepository.findByPublicProductId(publicProductId)).thenReturn(null);
        Exception exception = assertThrows(ProductNotFoundException.class, () -> {
            productService.get(publicProductId);
        });
    }
}
