package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.entity.Rating;
import com.rookie.ecommerce.repository.RatingRepository;
import com.rookie.ecommerce.service.ProductService;
import com.rookie.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    ProductService productService;

    public List<Rating> getUserRatings(Long userid) {
        return ratingRepository.findAllById_UserID(userid);
    }

    public List<Rating> getProductRatings(Long productid) {
        return ratingRepository.findAllById_ProductID(productid);
    }

    public List<Rating> getProductRatingOfUser(Long productid, Long userid) {
        return ratingRepository.findAllById_UserIDAndId_ProductID(userid, productid);
    }

    public Rating createRating(Rating rating) {
        rating.setDate(java.time.LocalDate.now());
        Rating savedRating = ratingRepository.save(rating);
        if (savedRating != null){
            updateRating(rating.getId().getProductID());
        }
        return savedRating;
    }

    @Transactional
    public Double updateRating(Long productID) {
        List<Rating> ratings = ratingRepository.findAllById_ProductID(productID);
        Double rating = ratings.stream().mapToDouble(Rating::getRate).average().orElse(Double.NaN);
        productService.updateRating(productID, rating);
        return rating;
    }
}
