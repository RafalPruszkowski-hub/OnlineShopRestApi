package com.store.app.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
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

    public ProductEntity() {
    }
}
