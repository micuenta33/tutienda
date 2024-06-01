package com.Tutienda.service.impl;

import com.Tutienda.entity.Rating;
import com.Tutienda.entity.product.Shoe;
import com.Tutienda.entity.users.User;
import com.Tutienda.repository.IShoeRepository;
import com.Tutienda.repository.IUserRepository;
import com.Tutienda.repository.RatingRepository;
import com.Tutienda.service.IRatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RatingService implements IRatingService {

    private final RatingRepository ratingRepository;
    private final IUserRepository userRepository;
    private final IShoeRepository shoeRepository;

    public RatingService(RatingRepository ratingRepository, IUserRepository userRepository, IShoeRepository shoeRepository) {
        this.ratingRepository = ratingRepository;
        this.userRepository = userRepository;
        this.shoeRepository = shoeRepository;
    }

    @Override
    public boolean hasRated(User user, Shoe shoe) {
        return ratingRepository.existsByUserAndShoe(user, shoe);
    }

    @Override
    @Transactional
    public boolean saveRating(String username, Long shoeId, int ratingValue, String comment) {
        Shoe shoe = shoeRepository.findById(shoeId)
                .orElseThrow(() -> new RuntimeException("Shoe not found"));
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!hasRated(user, shoe)) {
            Rating rating = Rating.builder().shoe(shoe).user(user).ratingValue(ratingValue).comment(comment).build();
            ratingRepository.save(rating);
            // Actualizar la valoración promedio del zapato
            shoe.getRatings().add(rating);
            shoe.updateRating();
            shoeRepository.save(shoe);

            return true;
        }
        return false;
    }
}
