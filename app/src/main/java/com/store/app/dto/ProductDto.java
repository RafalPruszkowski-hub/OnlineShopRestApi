package com.store.app.dto;

import com.store.app.database.entity.ProductEntity;
import org.springframework.beans.BeanUtils;

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

    public ProductDto(){}

    public ProductDto(ProductEntity productEntity){
        BeanUtils.copyProperties(productEntity,this);
    }

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

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(String productBrand) {
        this.productBrand = productBrand;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getQuantityOfStock() {
        return quantityOfStock;
    }

    public void setQuantityOfStock(int quantityOfStock) {
        this.quantityOfStock = quantityOfStock;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
