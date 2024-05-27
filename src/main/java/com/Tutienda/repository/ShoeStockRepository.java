package com.Tutienda.repository;

import com.Tutienda.entity.ShoeStock;
import com.Tutienda.entity.ShoeStockId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShoeStockRepository extends JpaRepository<ShoeStock, ShoeStockId> {
    List<ShoeStock> findAllByShoeId(Long id);
}
