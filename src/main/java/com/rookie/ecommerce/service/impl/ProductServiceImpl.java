package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.exception.ProductException.InvalidProductStatusException;
import com.rookie.ecommerce.exception.ProductException.ProductNotExistedException;
import com.rookie.ecommerce.repository.CategoryRepository;
import com.rookie.ecommerce.repository.ProductRespository;
import com.rookie.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRespository productRespository;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<Product> getProducts() {
        return productRespository.findAll();
    }

    public Product getProductByID(Long id) {
        return productRespository
                .findById(id)
                .orElseThrow(() -> new ProductNotExistedException(id));
    }

    public Product addProduct(Product product) {
        product.setCreatedDate(java.time.LocalDate.now());
        product.setUpdatedDate(java.time.LocalDate.now());
        product.setStatus("ENABLE");
        return productRespository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRespository.deleteById(productId);
    }

    public List<Product> getProductsByCategory(Long categoryID) {
        categoryRepository.findById(categoryID).orElseThrow(() -> new CategoryNotExistedException(categoryID));
        List<Product> products = productRespository.findByCategory_Id(categoryID);
        return products;
    }

    public List<Product> getProductsByStatus(String status) {
        if (!Product.PRODUCT_STATUS.contains(status)){
            throw new InvalidProductStatusException(status);
        }
        List<Product> products = productRespository.findByStatus(status);
        return products;
    }

    public Product updateProduct(Long productID, Product product) {
        if (!productRespository.existsById(productID)){
            return null;
        }
        product.setId(productID);
        product.setUpdatedDate(java.time.LocalDate.now());
        return productRespository.save(product);
    }

    @Override
    public ProductDTO convertToDTO(Product product) {
        if (product == null){
            return null;
        }
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setRating(product.getRating());
        productDTO.setThumbnail(product.getThumbnail());
        productDTO.setCategory(product.getCategory().getName());
        return productDTO;
    }

    @Override
    public Product convertToEntity(ProductDTO productDTO) {
        if(productDTO == null){
            return null;
        }
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setThumbnail(productDTO.getThumbnail());
        product.setPrice(productDTO.getPrice());
        product.setRating(productDTO.getRating());
        product.setCategory(categoryRepository.findByName(productDTO.getName()));
        return product;
    }


}
