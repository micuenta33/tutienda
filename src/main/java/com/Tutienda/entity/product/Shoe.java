package com.Tutienda.entity.product;

import com.Tutienda.entity.ShoeSize;
import com.Tutienda.entity.ShoeTypeEnum;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "shoes")
@DiscriminatorValue("shoes")
public class Shoe extends Product {
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "product_shoes_sizes",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "Shoe_Size_id"))
    private List<ShoeSize> shoeSizes;
    @Enumerated(EnumType.STRING)
    private ShoeTypeEnum type;
    private Integer stockSize35;
    private Integer stockSize36;
    private Integer stockSize37;
    private Integer stockSize38;
    private Integer stockSize39;
    private Integer stockSize40;
    private Integer stockSize41;
    private Integer stockSize42;
    private Integer stockSize43;
    private Integer stockSize44;
    private Integer stockSize45;
    private Integer stockSize46;

}
