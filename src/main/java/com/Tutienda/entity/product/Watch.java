package com.Tutienda.entity.product;

import com.Tutienda.entity.DisplayTypeEnum;
import com.Tutienda.entity.WatchTypeEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Data
@Entity
@Table(name = "watches")
@DiscriminatorValue("watch")
public class Watch extends Product {
    private WatchTypeEnum watchType;
    private boolean waterResistant;
}
