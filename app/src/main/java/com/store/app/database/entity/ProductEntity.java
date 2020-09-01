package com.store.app.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Entity(name = "products")
public class ProductEntity implements Serializable {
    private static final long serialVersionUID = 9L;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    @NotNull
    @Column
    private String publicProductId;

    @NotNull
    @Column
    private String productName;

    @NotNull
    @Column
    private String productBrand;

    @NotNull
    @Column
    private String productModel;

    @NotNull
    @Column
    private String productDescription;

    @NotNull
    @Column
    @Range(min = 0)
    private int quantityOfStock;

    @Column(precision = 10, scale = 2)
    @NotNull
    @Range(min = 0)
    private double productPrice;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartItemEntity> cartItems;


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

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer cartProductId) {
        this.productId = cartProductId;
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


    public List<CartItemEntity> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItemEntity> cartItems) {
        this.cartItems = cartItems;
    }

    public String getPublicProductId() {
        return publicProductId;
    }

    public void setPublicProductId(String publicProductId) {
        this.publicProductId = publicProductId;
    }
}
