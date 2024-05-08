package com.Tutienda.repository;

import com.Tutienda.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IItemRepository extends JpaRepository<Item, Long> {
}
