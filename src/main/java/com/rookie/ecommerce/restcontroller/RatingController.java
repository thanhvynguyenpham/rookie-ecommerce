package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Rating;
import com.rookie.ecommerce.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/ratings")
public class RatingController {

    @Autowired
    RatingService ratingService;

    @GetMapping("/user/{userid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Rating> getUserRatings(@PathVariable Long userid){
        return ratingService.getUserRatings(userid);
    }

    @GetMapping("/product/{productid}")
    public List<Rating> getProductRatings(@PathVariable Long productid){
        return ratingService.getProductRatings(productid);
    }

    @GetMapping()
    public List<Rating> getProductRatingOfUSer(@RequestParam Long productid, @RequestParam Long userid){
        return ratingService.getProductRatingOfUser(productid, userid);
    }

    @PostMapping()
    @PreAuthorize("hasRole('ROLE_USER')")
    public Rating createRating(@RequestBody Rating rating){
        return ratingService.createRating(rating);
    }
}
