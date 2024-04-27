package com.Tutienda.repository;

import com.Tutienda.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IShoeSizeRepository extends JpaRepository<ShoeSize, Long> {
    List<ShoeSize> findAllByShoeSizeIn(List<ShoeSizeEnum> shoeSizes);
}
