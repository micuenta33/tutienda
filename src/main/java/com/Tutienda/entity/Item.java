package com.Tutienda.entity;


import com.Tutienda.entity.product.Shoe;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "items")
public class Item  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "shoe_id")
    private Shoe shoe;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id")
    @ToString.Exclude
    private Purchase purchase;
    private Integer size;
    private int quantity;
    private double totalPrice;
    private boolean hasRated;
}
