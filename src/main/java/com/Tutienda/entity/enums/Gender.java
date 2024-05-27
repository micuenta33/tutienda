package com.Tutienda.entity.enums;

import lombok.Getter;

import java.io.Serializable;

@Getter
public enum Gender  {
    HOMBRE("Hombre"),
    MUJER("Mujer"),
    UNISEX("Unisex");

    private final String genderEnum;

    Gender(String gender) {
        this.genderEnum = gender;
    }
}
