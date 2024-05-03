package com.Tutienda.entity;

public enum DisplayTypeEnum {
    ANALOG("Analog"),
    DIGITAL("Digital"),
    LCD("LCD"),
    OLED("OLED"),
    TFT("TFT"),
    AMOLED("AMOLED");
    private final String displayName;

    DisplayTypeEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
