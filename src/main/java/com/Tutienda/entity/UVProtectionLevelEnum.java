package com.Tutienda.entity;

import java.util.Locale;

public enum UVProtectionLevelEnum {
    UV_0("0UV No protección"),
    UV_100("100UV Baja protección"),
    UV_200("200UV Protección media"),
    UV_300("300UV Protección media "),
    UV_400("400UV Alta protección");

    private final String description;

    UVProtectionLevelEnum(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
