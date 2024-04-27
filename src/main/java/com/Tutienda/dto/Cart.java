package com.Tutienda.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@Component
public class Cart {
    private List<CarItem>  carItems;
    private int quantityProduct;

    public Cart() {
        carItems = new ArrayList<>();
    }
    public Cart(List<CarItem> carItems, int quantityProduct) {
        this.carItems = carItems;
        this.quantityProduct = quantityProduct;
    }

    public Double getTotalCart() {
        BigDecimal number = new BigDecimal(carItems.stream().mapToDouble((CarItem::getPriceQuantity)).sum());
        int decimals = 2;
        BigDecimal rounded = number.setScale(decimals, RoundingMode.HALF_UP);
        return rounded.doubleValue();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "carItems=" + carItems +
                ", quantityProduct=" + quantityProduct +
                '}';
    }
}
