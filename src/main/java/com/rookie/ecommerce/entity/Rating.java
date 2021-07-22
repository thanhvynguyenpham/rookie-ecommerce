package com.rookie.ecommerce.entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ratings")
public class Rating{
    @EmbeddedId
    private RatingID id;

    @Embeddable
    public static class RatingID implements Serializable {

        @Column(name = "userid")
        Long userID;

        @Column(name = "productid")
        Long productID;

        public RatingID() {
            super();
        }

        public RatingID(Long userID, Long productID) {
            super();
            this.userID = userID;
            this.productID = productID;
        }


        public Long getUserID() {
            return userID;
        }

        public void setUserID(Long userID) {
            this.userID = userID;
        }

        public Long getProductID() {
            return productID;
        }

        public void setProductID(Long productID) {
            this.productID = productID;
        }
    }

    private Double rate;

    private String comment;

    private LocalDate date;

    public Rating() {
    }

    public Rating(RatingID id, Double rate, String comment, LocalDate date) {
        this.id = id;
        this.rate = rate;
        this.comment = comment;
        this.date = date;
    }

    public RatingID getId() {
        return id;
    }

    public void setId(RatingID id) {
        this.id = id;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}

