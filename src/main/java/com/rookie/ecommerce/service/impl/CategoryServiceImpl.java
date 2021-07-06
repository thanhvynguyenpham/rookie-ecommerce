package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.exception.CategoryException.CategoryAlreadyExistedException;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.repository.CategoryRepository;
import com.rookie.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public Category getCategoryByID(Long categoryID) {
        return categoryRepository.findById(categoryID).orElseThrow(() -> new CategoryNotExistedException(categoryID));
    }

    public void createCategory(Category category) {
        Optional<Category> categoryOptional = categoryRepository.findByName(category.getName());
        if (categoryOptional.isPresent()) {
            throw new CategoryAlreadyExistedException(category.getName());
        }
        category.setStatus("ENABLE");
        categoryRepository.save(category);
    }

    @Transactional
    public void updateCategory(Long categoryID, String name, String description, String status) {
        Category category = categoryRepository
                .findById(categoryID)
                .orElseThrow(() -> new CategoryNotExistedException(categoryID));
        if (name != null
                && name.length() > 0
                && !Objects.equals(name, category.getName())) {
            category.setName(name);
        }
        if (description != null
                && description.length() > 0
                && category.getDescription() != null
                && !Objects.equals(description, category.getDescription())) {
            category.setDescription(description);
        }
        if (status != null
                && status.length() > 0
                && Category.CATEGORY_STATUS.contains(status)
                && !Objects.equals(status, category.getStatus())) {
            category.setStatus(status);
        }
        categoryRepository.save(category);
    }

    public void deleteCategory(Long categoryID) {
        if (categoryRepository.findById(categoryID).isEmpty()) {
            throw new CategoryNotExistedException(categoryID);
        }
        categoryRepository.deleteById(categoryID);
    }
}
