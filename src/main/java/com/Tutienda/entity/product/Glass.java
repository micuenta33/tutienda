package com.Tutienda.entity.product;

import com.Tutienda.entity.enums.UVProtectionLevelEnum;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "glasses")
@DiscriminatorValue("glass")
public class Glass extends Product {
    private String lensMaterial;
    private boolean polarized;
    private UVProtectionLevelEnum UVProtectionLevel;
}
