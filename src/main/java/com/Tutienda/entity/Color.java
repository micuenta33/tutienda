package com.Tutienda.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


public class Color {

    private Long id;
    private String name;

    public Color(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Color() {
    }
}
