package com.Tutienda.entity.users;

import jakarta.persistence.*;
import lombok.*;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "address_db")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String country;
    private String community;
    private String province;
    private String city;
    private String zipCode;
    private String street;
    private String number;
    private String floor;
    private String door;
//    @OneToOne
//    @JoinColumn(name = "user_id")
//    private User user;

}
