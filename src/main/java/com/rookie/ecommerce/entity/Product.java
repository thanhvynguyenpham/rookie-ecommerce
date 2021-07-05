package com.rookie.ecommerce.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "Products")
public class Product {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "prod_cat_fk")
    private Category category;

    @Column(name="createddate")
    private LocalDate createdDate;

    @Column(name="updateddate")
    private LocalDate updatedDate;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "status")
    private String status;

    @Column(name = "rate")
    private Double rating;

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, Category category, LocalDate createdDate, LocalDate updatedDate, String thumbnail, String status, Double rating) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.thumbnail = thumbnail;
        this.status = status;
        this.rating = rating;
    }

    public Product(String name, String description, Double price, Category category, String thumbnail, String status) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.thumbnail = thumbnail;
        this.status = status;
        this.createdDate = java.time.LocalDate.now();
        this.updatedDate = java.time.LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDate getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(LocalDate updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
}
