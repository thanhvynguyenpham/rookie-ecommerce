package com.rookie.ecommerce.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "Products")
public class Product {
    public static ArrayList<String> PRODUCT_STATUS = new ArrayList<>(Arrays. asList("ENABLE", "DISABLE", "HIDDEN")) ;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "products_seq")
    @SequenceGenerator(name = "products_seq", sequenceName = "products_seq", allocationSize=1)
    @Column(name="id")
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="price")
    private Double price;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;

    @Column(name="createddate")
    private LocalDate createdDate;

    @Column(name="updateddate")
    private LocalDate updatedDate;

    @Column(name = "thumbnail")
    private byte[] thumbnail;

    @Column(name = "status")
    private String status;

    @Column(name = "rate")
    private Double rating;

//    @OneToMany(mappedBy = "productid", cascade = CascadeType.ALL)
//    @JsonIgnore
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    private List<Product> products = new ArrayList<>();

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, Category category, LocalDate createdDate, LocalDate updatedDate, byte[] thumbnail, String status, Double rating) {
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

    public Product(String name, String description, Double price, Category category, byte[] thumbnail, String status) {
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

    public byte[] getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(byte[] thumbnail) {
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
