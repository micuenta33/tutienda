package com.Tutienda.entity.product;


import com.Tutienda.entity.ClothingSize;
import com.Tutienda.entity.enums.ClothingTypeEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "clothing")
@DiscriminatorValue("clothing")
public class Clothing extends Product {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_clothing_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "Clothing_size_id"))
    private List<ClothingSize> clothingSizes;

    @Column(name = "stock_size_xsmall")
    private Integer stockSizeXSmall;
    @Column(name = "stock_size_small")
    private Integer stockSizeSmall;
    @Column(name = "stock_size_medium")
    private Integer stockSizeMedium;
    @Column(name = "stock_size_large")
    private Integer stockSizeLarge;
    @Column(name = "stock_size_xlarge")
    private Integer stockSizeXLarge;
    @Column(name = "stock_size_xxl")
    private Integer stockSizeXXLarge;

    @Enumerated(EnumType.STRING)
    private ClothingTypeEnum type;

    @Override
    protected Integer stockTotal() {
        Integer stock = stockSizeSmall + stockSizeMedium + stockSizeLarge + stockSizeXLarge + stockSizeXXLarge ;
        return stock;
    }


}
