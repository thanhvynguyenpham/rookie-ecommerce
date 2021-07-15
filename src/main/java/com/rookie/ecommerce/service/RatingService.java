package com.rookie.ecommerce.service;

import com.rookie.ecommerce.entity.Rating;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RatingService {
    List<Rating> getUserRatings(Long userid);

    List<Rating> getProductRatings(Long productid);

    List<Rating> getProductRatingOfUser(Long productid, Long userid);

    Rating createRating(Rating rating);

    Double updateRating(Long productID);
}
