package com.Tutienda.repository;

import com.Tutienda.entity.enums.ShoeTypeEnum;
import com.Tutienda.entity.product.Shoe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IShoeRepository extends JpaRepository<Shoe,Long> {
    List<Shoe> findByRatingGreaterThanEqual(Integer rating );
    List<Shoe> findAllByType(ShoeTypeEnum type);
}
