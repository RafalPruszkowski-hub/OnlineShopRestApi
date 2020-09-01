package com.store.app.dto;

import java.io.Serializable;

public class ProductDto implements Serializable {
    private static final long serialVersionUID = -4133333793091890797L;
    private Integer productId;
    private String publicProductId;
    private String productName;
    private String productBrand;
    private String productModel;
    private String productDescription;
    private int quantityOfStock;
    private double productPrice;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getPublicProductId() {
        return publicProductId;
    }

    public void setPublicProductId(String publicProductId) {
        this.publicProductId = publicProductId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public void setQuantityOfStock(int quantityOfStock) {
        this.quantityOfStock = quantityOfStock;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public int getQuantityOfStock() {
        return quantityOfStock;
    }

    public double getProductPrice() {
        return productPrice;
    }
}
