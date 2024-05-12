package com.Tutienda.service;


import com.Tutienda.entity.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IProductService {
    Page<Product> findAll(Pageable pageable);
    List<Product> findAll();
    Page<Product> findFilteredAndPaginatedProducts(int page, String categoria, String gender,String type, int pageSize);

    Product save(Product product);
    void deleteById(Long id);
    Optional<Product> findById(Long id);
    public List<Product> findByBestRatingGreaterThanEqual(Integer rating);

    List<Product> findByName(String term);
}
