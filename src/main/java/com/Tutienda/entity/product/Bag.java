package com.Tutienda.entity.product;

import com.Tutienda.entity.enums.BagTyoeEnum;

public class Bag extends Product {

    private BagTyoeEnum bagType;
    private String material;
    private Integer stock;

    @Override
    protected Integer stockTotal() {
        return this.stock;
    }
}
