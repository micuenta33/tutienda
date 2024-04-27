package com.Tutienda.repository;

import com.Tutienda.entity.ImageUrl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IImageUrlRepository extends JpaRepository<ImageUrl, Long> {
}
