package com.Tutienda.service;


import com.Tutienda.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
    Product save(Product product);
    void deleteById(Long id);
    Optional<Product> findById(Long id);

    List<Product> findAllProductsByIds(List<Long> idsProductos);

    List<Product> findAllByBestRating(Integer rating);
}
