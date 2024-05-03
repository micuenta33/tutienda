package com.Tutienda.entity;

public enum WatchTypeEnum {
    SMART("Smartwatch"),
    ANALOG("Reloj Analógico"),
    DIGITAL("Reloj Digital"),
    SPORT("Reloj Deportivo"),
    DRESS("Reloj de Vestir");

    private final String type;

    WatchTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
