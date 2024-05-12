package com.Tutienda.entity.enums;

import lombok.Getter;

@Getter
public enum ClothingTypeEnum {
    CAMISA("Camisa"),
    CAMISETA("Camiseta"),
    PANTALONES("Pantalones"),
    LEGGINGS("Leggings"),
    PANTALONES_CORTOS("Pantalones cortos"),
    VAQUEROS("Vaqueros"),
    FALDA("Falda"),
    CHAQUETA("Chaqueta"),
    CHAQUETA_LIGERA("Chaqueta ligera"),
    ROPA_DEPORTIVA("Ropa deportiva"),
    POLO("Polo"),
    SUDADERA("Sudadera"),
    JERSEY("Jersey"),
    VESTIDO("Vestido"),
    OTROS("Otros");

    private final String typeEnum;

    ClothingTypeEnum(String type) {
        this.typeEnum = type;
    }

}
