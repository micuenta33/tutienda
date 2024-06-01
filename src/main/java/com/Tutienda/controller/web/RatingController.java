package com.Tutienda.controller.web;

import com.Tutienda.entity.Rating;
import com.Tutienda.entity.RatingRequestDTO;
import com.Tutienda.service.IRatingService;
import com.Tutienda.service.impl.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Map;

@RestController
public class RatingController {
    @Autowired
    private IRatingService ratingService;


    @PostMapping("/rate-product")
    public ResponseEntity<?> rateProduct(@RequestBody RatingRequestDTO ratingRequest, Principal principal) {
        String username = principal.getName();
        Long shoeId = ratingRequest.getShoeId();
         boolean success = ratingService.saveRating(username, shoeId, ratingRequest.getRatingValue(), ratingRequest.getComment());
        if (!success) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("success", false));
        }
        return ResponseEntity.ok(Map.of("success", true));
    }
}
