package com.rookie.ecommerce.service;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    List<Product> getProducts();

    List<Product> getTop6ProductsByRating();

    Optional<Product> getProductByID(Long id);

    Product addProduct(Product product);

    void deleteProduct(Long productId);

    List<Product> getProductsByCategory(Long categoryID);

    List<Product> getProductsByStatus(String status);

    Product updateProduct(Long productID, Product product);

    Product updateRating(Long productID, Double rate);

    ProductDTO convertToDTO(Product product);

    Product convertToEntity(ProductDTO productDTO);
}
