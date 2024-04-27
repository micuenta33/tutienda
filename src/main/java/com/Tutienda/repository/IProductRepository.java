package com.Tutienda.repository;


import com.Tutienda.entity.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IProductRepository extends JpaRepository<Product,Long> {

    List<Product> findAllByRatingGreaterThan(Integer rating );
}
