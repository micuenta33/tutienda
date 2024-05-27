package com.Tutienda.entity.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum ShoeTypeEnum  {
    ZAPATOS("Zapatos"),
    ZAPATOS_CLÁSICO("Zapatos clásicos"),
    ZAPATILLAS("Zapatillas"),
    ZAPATILLAS_DEPORTES("Zapatillas deportivas"),
    SANDALIAS("Sandalías"),
    BOTINES("Botines"),
    TACONES("Tacones"),
    PANTUFLAS("Pantuflas"),
    BOTAS("Botas"),
    OTROS("Otros");

    private final String typeEnum;

    ShoeTypeEnum(String type) {
        this.typeEnum = type;
    }
}
