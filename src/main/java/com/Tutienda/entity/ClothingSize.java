package com.Tutienda.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clothing_sizes")
public class ClothingSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ClothingSizeEnum clothingSize;

    @Override
    public String toString() {
        return "ClothingSize{" +
                "clothingSize=" + clothingSize +
                ", id=" + id +
                '}';
    }
}
