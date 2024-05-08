package com.Tutienda.repository;

import com.Tutienda.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {

}
