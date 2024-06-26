package com.Tutienda.service;


import com.Tutienda.entity.enums.ShoeTypeEnum;
import com.Tutienda.entity.product.Shoe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IShoeService {
    List<Shoe> findAll();
    Page<Shoe> findFilteredAndPaginatedProducts(int page, String gender,String type, int pageSize);
    Shoe save(Shoe shoe);
    Optional<Shoe> findById(Long id);
    public List<Shoe> findByBestRatingGreaterThanEqual(Integer rating);
    void deleteById(Long id);
    List<Shoe> findAllByType(ShoeTypeEnum type);
}
