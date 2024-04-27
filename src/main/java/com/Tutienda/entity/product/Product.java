package com.Tutienda.entity.product;

import com.Tutienda.entity.Gender;
import com.Tutienda.entity.ImageUrl;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Data
@Entity
@Table(name = "products")
@DiscriminatorColumn(name="product_type")
@Inheritance(strategy=InheritanceType.JOINED)
public  class Product  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imagePrimary;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private List<ImageUrl> imageUrl;
    private String name;
    private String brand;
    private int rating;
    private double price;
    @Column(length = 10000)
    private String description;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private String color;
    private LocalDateTime createdAt;

    protected Integer stockTotal(){
        Integer stock = 0;
        return stock;
    }

}
