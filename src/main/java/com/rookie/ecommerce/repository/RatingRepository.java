package com.rookie.ecommerce.repository;

import com.rookie.ecommerce.entity.Rating;
import com.rookie.ecommerce.entity.Rating.RatingID;
import org.apache.catalina.LifecycleState;
import org.hibernate.query.criteria.internal.expression.ListIndexExpression;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RatingRepository extends JpaRepository<Rating, RatingID> {
    List<Rating> findAllById_UserID(Long userid);

    List<Rating> findAllById_ProductID(Long productID);

    List<Rating> findAllById_UserIDAndId_ProductID(Long userID, Long productID);

}
