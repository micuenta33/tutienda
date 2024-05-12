package com.Tutienda.entity.enums;

import lombok.Getter;

@Getter
public enum ShoeTypeEnum {
    CLÁSICO("Zapatos"),
    ZAPATOS_CLÁSICO("Zapatos clásicos"),
    ZAPATILLAS("Zapatillas"),
    ZAPATILLAS_DEPORTES("Zapatillas deportivas"),
    SANDALIAS("Sandalías"),
    BOTINES("Botines"),
    TACONES("Tacones"),
    BOTAS("Botas"),
    OTROS("Otros");

    private final String typeEnum;

    ShoeTypeEnum(String type) {
        this.typeEnum = type;
    }
}
