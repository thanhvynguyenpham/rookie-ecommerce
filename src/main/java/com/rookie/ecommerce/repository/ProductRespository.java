package com.rookie.ecommerce.repository;

import com.rookie.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRespository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_Id(Long categoryID);

    List<Product> findByStatus(String status);
}
