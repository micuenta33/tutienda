package com.Tutienda.entity;

public enum Gender {
    HOMBRE("Hombre"),
    MUJER("Mujer"),
    UNISEX("Unisex");

    private final String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
