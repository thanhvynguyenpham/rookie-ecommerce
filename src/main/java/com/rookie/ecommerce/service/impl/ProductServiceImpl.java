package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.exception.CategoryException.CategoryAlreadyExistedException;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.exception.ProductException.InvalidProductStatusException;
import com.rookie.ecommerce.exception.ProductException.ProductNotExistedException;
import com.rookie.ecommerce.repository.CategoryRepository;
import com.rookie.ecommerce.repository.ProductRespository;
import com.rookie.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRespository productRespository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(ProductRespository productRespository, CategoryRepository categoryRepository) {

        this.productRespository = productRespository;
        this.categoryRepository = categoryRepository;
    }

    public List<Product> getProducts() {
        return productRespository.findAll();
    }

    public Product getProductByID(Long id) {
        return productRespository
                .findById(id)
                .orElseThrow(() -> new ProductNotExistedException(id));
    }

    public void addProduct(Product product, Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow(()-> new CategoryNotExistedException(categoryId));
        product.setCategory(category);
        product.setCreatedDate(java.time.LocalDate.now());
        product.setUpdatedDate(java.time.LocalDate.now());
        product.setStatus("ENABLE");
        productRespository.save(product);
    }

    public void addProducts(List<Product> products, Long categoryId) {
        products.forEach(product -> addProduct(product,categoryId));
    }

    @Transactional
    public void updateProduct(Long productID, String name, String description, Long categoryID, String thumbnail) {
        Product product = productRespository
                .findById(productID)
                .orElseThrow(()-> new ProductNotExistedException(productID));
        if (categoryID != null){
            Category category = categoryRepository
                    .findById(categoryID)
                    .orElseThrow(()-> new CategoryNotExistedException(productID));
            product.setCategory(category);
        }
        if (name != null
                && name.length() != 0
                && !Objects.equals(name, product.getName())){
            product.setName(name);
        }
        if (description != null
                && description.length() != 0
                && !Objects.equals(description, product.getDescription())){
            product.setDescription(description);
        }
        if (thumbnail != null
                && thumbnail.length() != 0
                && Objects.equals(thumbnail, product.getThumbnail())){
            product.setThumbnail(thumbnail);
        }
        productRespository.save(product);
    }

    public void updateProductStatus(Long productId, String status) {
        if (!Product.PRODUCT_STATUS.contains(status)){
            throw new InvalidProductStatusException(status);
        }
        Product product = productRespository
                .findById(productId)
                .orElseThrow(()-> new ProductNotExistedException(productId));
        if (status != product.getStatus()){
            product.setStatus(status);
        }
        productRespository.save(product);
    }

    public void deleteProduct(Long productId) {
        productRespository.findById(productId)
                .orElseThrow(()->new ProductNotExistedException(productId));
        productRespository.deleteById(productId);
    }

}
