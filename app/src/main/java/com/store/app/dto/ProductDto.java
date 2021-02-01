package com.store.app.dto;

import com.store.app.database.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
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

    public ProductDto() {
    }

    public ProductDto(ProductEntity productEntity) {
        BeanUtils.copyProperties(productEntity, this);
    }

}
