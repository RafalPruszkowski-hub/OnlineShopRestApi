package com.store.app.model.response;

import com.store.app.dto.ProductDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
public class ProductResponseModel {
    private String publicProductId;
    private String productName;
    private String productBrand;
    private String productModel;
    private String productDescription;
    private int quantityOfStock;
    private double productPrice;

    public ProductResponseModel() {
    }
    public ProductResponseModel(ProductDto product) {
        BeanUtils.copyProperties(product,this);
    }
}

