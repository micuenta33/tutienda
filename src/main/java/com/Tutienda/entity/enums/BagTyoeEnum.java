package com.Tutienda.entity.enums;

import lombok.Getter;

@Getter
public enum BagTyoeEnum {
    MOCHILA("mochila"),
    BOLSO("bolso");

    private final String typeEnum;

    BagTyoeEnum(String typeEnum) {
        this.typeEnum = typeEnum;
    }

}
