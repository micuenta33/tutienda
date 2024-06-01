package com.Tutienda.repository;

import com.Tutienda.entity.Rating;
import com.Tutienda.entity.product.Shoe;
import com.Tutienda.entity.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
    boolean existsByUserAndShoe(User user, Shoe shoe);
}
