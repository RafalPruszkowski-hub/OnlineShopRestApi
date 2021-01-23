package com.store.app.model.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDetailsRequestModel {
    private String productName;
    private String productBrand;
    private String productModel;
    private String productDescription;
    private int quantityOfStock;
    private double productPrice = 0;
}
