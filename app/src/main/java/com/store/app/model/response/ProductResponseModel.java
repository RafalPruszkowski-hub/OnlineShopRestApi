package com.store.app.model.response;

public class ProductResponseModel {
    private String publicProductId;
    private String productName;
    private String productBrand;
    private String productModel;
    private String productDescription;
    private int quantityOfStock;
    private double productPrice;

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

    @Override
    public String toString() {
        return "ProductResponseModel{" +
                "publicProductItemId='" + publicProductId + '\'' +
                ", productName='" + productName + '\'' +
                ", productBrand='" + productBrand + '\'' +
                ", productModel='" + productModel + '\'' +
                ", productDescription='" + productDescription + '\'' +
                ", quantityOfStock=" + quantityOfStock +
                ", productPrice=" + productPrice +
                '}';
    }
}

