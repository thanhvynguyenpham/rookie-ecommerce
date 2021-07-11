package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.exception.CategoryException.CategoryAlreadyExistedException;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.exception.CategoryException.InvalidCategoryStatusException;
import com.rookie.ecommerce.exception.CategoryException.NoCategoryException;
import com.rookie.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){

        return categoryService.getCategories();
    }

    @GetMapping("/status/{status}")
    public List<Category> getCategoriesByStatus(@PathVariable String status){
        if (!Category.CATEGORY_STATUS.contains(status)){
            throw new InvalidCategoryStatusException(status);
        }
        List<Category> categories = categoryService.getCategoriesByStatus(status);
        if (categories.isEmpty()){
            throw new NoCategoryException(status);
        }
        return categories;
    }

    @GetMapping("/{categoryID}")
    public Category getCategoryByID(@PathVariable Long categoryID){
        return categoryService
                .getCategoryByID(categoryID)
                .orElseThrow(() -> new CategoryNotExistedException(categoryID));
    }

    @PostMapping
    public void createCategory(@RequestBody Category category){

        if (!categoryService.createCategory(category)){
            throw new CategoryAlreadyExistedException(category.getName());
        }
    }

    @PutMapping("/{categoryID}")
    public Category updateCategory(@PathVariable Long categoryID,
                                  @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(categoryID, category);
        if (updatedCategory != null){
            throw new CategoryNotExistedException(categoryID);
        }
        return category;
    }

    @DeleteMapping("/{categoryID}")
    public void deleteCategory(@PathVariable Long categoryID){
        if (!categoryService.deleteCategory(categoryID)){
            throw new CategoryNotExistedException(categoryID);
        };
    }
}
