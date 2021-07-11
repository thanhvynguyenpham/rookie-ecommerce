package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.exception.CategoryException.InvalidCategoryStatusException;
import com.rookie.ecommerce.exception.CategoryException.NoCategoryException;
import com.rookie.ecommerce.repository.CategoryRepository;
import com.rookie.ecommerce.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {

        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryByID(Long categoryID) {
        return categoryRepository.findById(categoryID);

    }

    public boolean createCategory(Category category) {
        Optional<Category> categoryOptional = Optional.ofNullable(categoryRepository.findByName(category.getName()));
        if (categoryOptional.isPresent()) {
            return false;
        }
        category.setStatus("ENABLE");
        categoryRepository.save(category);
        return true;
    }

    public boolean deleteCategory(Long categoryID) {
        if (categoryRepository.existsById(categoryID)) {
            return false;
        }
        categoryRepository.deleteById(categoryID);
        return true;
    }

    public List<Category> getCategoriesByStatus(String status) {
        return categoryRepository.findByStatus(status);
    }

    public Category updateCategory(Long categoryID, Category category) {
        if (!categoryRepository.existsById(categoryID)){
            return null;
        }
        category.setId(categoryID);
        categoryRepository.save(category);
        return category;
    }

//    public boolean updateCategory(Category category) {
//        categoryRepository.save()
//    }
}
