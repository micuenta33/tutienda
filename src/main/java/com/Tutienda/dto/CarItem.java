package com.Tutienda.dto;

import com.Tutienda.entity.product.Product;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
public class CarItem {

    private Product product;
    private int quantity;
    private double price;

    public CarItem() {
    }

    public CarItem(Product product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    public Double getPriceQuantity() {
        BigDecimal number = new BigDecimal(quantity * product.getPrice());
        int decimals = 2;
        BigDecimal rounded = number.setScale(decimals, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    @Override
    public String toString() {
        return "CarItem{" +
                "product=" + product +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
