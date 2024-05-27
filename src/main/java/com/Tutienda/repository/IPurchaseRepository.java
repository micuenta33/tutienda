package com.Tutienda.repository;

import com.Tutienda.entity.Purchase;
import com.Tutienda.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IPurchaseRepository extends JpaRepository<Purchase, Long> {
    List<Purchase> findAllByUser(User user);
}

