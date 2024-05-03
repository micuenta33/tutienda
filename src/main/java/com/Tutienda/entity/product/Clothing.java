package com.Tutienda.entity.product;


import com.Tutienda.entity.ClothingSize;
import com.Tutienda.entity.ClothingTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Data
@Entity
@Table(name = "clothing")
@DiscriminatorValue("clothing")
public class Clothing extends Product {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_clothing_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "Clothing_size_id"))
    private List<ClothingSize> clothingSizes;

    private Integer stockSizeXSmall;
    private Integer stockSizeSmall;
    private Integer stockSizeMedium;
    private Integer stockSizeLarge;
    private Integer stockSizeXLarge;
    private Integer stockSizeXXLarge;

    @Enumerated(EnumType.STRING)
    private ClothingTypeEnum type;

    @Override
    protected Integer stockTotal() {
        Integer stock = stockSizeSmall + stockSizeMedium + stockSizeLarge + stockSizeXLarge + stockSizeXXLarge ;
        return stock;
    }


}
