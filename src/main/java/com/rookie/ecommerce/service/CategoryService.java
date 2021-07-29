package com.rookie.ecommerce.service;

import com.rookie.ecommerce.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> getCategories();

    Optional<Category> getCategoryByID(Long categoryID);

    boolean createCategory(Category category);

    boolean deleteCategory(Long categoryID);

    Category updateCategory(Long categoryID, Category category);
}
