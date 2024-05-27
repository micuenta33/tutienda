package com.Tutienda.service;


import com.Tutienda.entity.product.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IShoeService {
    Page<Shoe> findAll(Pageable pageable);
    List<Shoe> findAll();
    Page<Shoe> findFilteredAndPaginatedProducts(int page, String gender,String type, int pageSize);

    Shoe save(Shoe shoe);
    void deleteById(Long id);
    Optional<Shoe> findById(Long id);
    public List<Shoe> findByBestRatingGreaterThanEqual(Integer rating);

    List<Shoe> findByName(String term);
}
