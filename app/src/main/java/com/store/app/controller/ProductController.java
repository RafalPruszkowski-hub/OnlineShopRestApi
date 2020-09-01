package com.store.app.controller;


import com.store.app.dto.ProductDto;
import com.store.app.model.request.ProductDetailsRequestModel;
import com.store.app.model.response.ProductResponseModel;
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

    @PostMapping
    public ProductResponseModel getProduct(@RequestBody ProductDetailsRequestModel productDetailsRequestModel) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productDetailsRequestModel, productDto);


        ProductDto createdProduct = productService.createProduct(productDto);
        BeanUtils.copyProperties(createdProduct, returnValue);

        return returnValue;
    }


    @GetMapping(path = "/{id}")
    public ProductResponseModel getProduct(@PathVariable(name = "id") String id) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = productService.getProduct(id);
        BeanUtils.copyProperties(productDto, returnValue);

        return returnValue;
    }

    @PutMapping(path = "/{id}")
    public ProductResponseModel updateProduct(@PathVariable(name = "id") String id, @RequestBody ProductDetailsRequestModel productDetailsRequestModel) {
        ProductResponseModel returnValue = new ProductResponseModel();

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(productDetailsRequestModel, productDto);


        ProductDto createdProduct = productService.updateProduct(productDto, id);
        BeanUtils.copyProperties(createdProduct, returnValue);

        return returnValue;
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable(name = "id") String id) {
        productService.deleteProduct(id);
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
