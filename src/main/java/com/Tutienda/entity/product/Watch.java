package com.Tutienda.entity.product;

import com.Tutienda.entity.enums.WatchTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "watches")
@DiscriminatorValue("watch")
public class Watch extends Product {
    private WatchTypeEnum watchType;
    private boolean waterResistant;
    private Integer stock;

    @Override
    protected Integer stockTotal() {
        return this.stock;
    }
}
