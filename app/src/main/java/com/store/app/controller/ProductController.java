package com.store.app.controller;


import com.store.app.dto.CartDto;
import com.store.app.dto.ProductDto;
import com.store.app.model.request.ProductDetailsRequestModel;
import com.store.app.model.response.CartResponseModel;
import com.store.app.model.response.ProductResponseModel;
import com.store.app.service.CartService;
import com.store.app.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;
    @Autowired
    CartService cartService;

    @PostMapping
    public ProductResponseModel createProduct(@RequestBody ProductDetailsRequestModel productDetailsRequestModel) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productDetailsRequestModel, productDto);


        ProductDto createdProduct = productService.createProduct(productDto);
        BeanUtils.copyProperties(createdProduct, returnValue);

        return returnValue;
    }


    @GetMapping(path = "/{productId}")
    public ProductResponseModel getProduct(@PathVariable(name = "productId") String productId) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = productService.getProduct(productId);
        BeanUtils.copyProperties(productDto, returnValue);

        return returnValue;
    }

    @PutMapping(path = "/{productId}")
    public ProductResponseModel updateProduct(@PathVariable(name = "productId") String productId, @RequestBody ProductDetailsRequestModel productDetailsRequestModel) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productDetailsRequestModel, productDto);
        productDto.setPublicProductId(productId);


        ProductDto createdProduct = productService.updateProduct(productDto, productId);
        BeanUtils.copyProperties(createdProduct, returnValue);

        return returnValue;
    }

    @GetMapping
    public List<ProductResponseModel> getProducts(
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "limit", defaultValue = "25") int limit
    ) {
        List<ProductResponseModel> returnValue = new ArrayList();

        List<ProductDto> productDtoList = productService.getProducts(page, limit);

        for (ProductDto productDto : productDtoList) {
            ProductResponseModel productResponseModel = new ProductResponseModel();
            BeanUtils.copyProperties(productDto, productResponseModel);
            returnValue.add(productResponseModel);
        }

        return returnValue;
    }
}
