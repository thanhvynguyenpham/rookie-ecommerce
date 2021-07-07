package com.rookie.ecommerce.service;

import com.rookie.ecommerce.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getCategories();

    Category getCategoryByID(Long categoryID);

    void createCategory(Category category);

    void updateCategory(Long categoryID, String name, String description, String status);

    void deleteCategory(Long categoryID);

    List<Category> getCategoriesByStatus(String status);
}
