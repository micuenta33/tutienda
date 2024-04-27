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
@Table(name = "Shoes_sizes")
public class ShoeSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private ShoeSizeEnum shoeSize;

    @Override
    public String toString() {
        return "ShoeSize{" +
                "id=" + id +
                ", shoeSize=" + shoeSize +
                '}';
    }
}
