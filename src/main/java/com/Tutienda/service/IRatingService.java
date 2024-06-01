package com.Tutienda.service;

import com.Tutienda.entity.product.Shoe;
import com.Tutienda.entity.users.User;

public interface IRatingService {
    boolean saveRating(String username, Long shoeId, int ratingValue, String comment);

    public boolean hasRated(User user, Shoe shoe);
}
