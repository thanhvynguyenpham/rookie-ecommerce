package com.rookie.ecommerce.service;

import com.rookie.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    List<Product> getProducts();

    Product getProductByID(Long id);

    void addProduct(Product product, Long categoryId);

    void addProducts(List<Product> products, Long categoryId);

    void updateProduct(Long productID, String name, String description, Long categoryID, String thumbnail);

    void updateProductStatus(Long productId, String status);

    void deleteProduct(Long productId);
}
