package com.store.app.model.response;

import com.store.app.dto.ProductDto;
import org.springframework.beans.BeanUtils;

public class CartItemResponseModel {
    private String publicCartItemId;
    private int quantity;
    private double productsPrice;

    private ProductResponseModel product;

    public double getProductsPrice() {
        return productsPrice;
    }

    public void setProductsPrice(double productsPrice) {
        this.productsPrice = productsPrice;
    }

    public String getPublicCartItemId() {
        return publicCartItemId;
    }

    public void setPublicCartItemId(String publicCartItemId) {
        this.publicCartItemId = publicCartItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductResponseModel getProduct() {
        return product;
    }

    public void setProduct(ProductResponseModel product) {
        this.product = product;
    }

    public void setProduct(ProductDto productDto) {
        ProductResponseModel tmp = new ProductResponseModel();
        BeanUtils.copyProperties(productDto, tmp);
        this.product = tmp;
    }

    @Override
    public String toString() {
        return "CartItemResponseModel{" +
                "publicCartItemId='" + publicCartItemId + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
