package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.exception.CategoryException.CategoryAlreadyExistedException;
import com.rookie.ecommerce.exception.CategoryException.CategoryNotExistedException;
import com.rookie.ecommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(path = "api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getCategories(){

        return categoryService.getCategories();
    }



    @GetMapping("/{categoryID}")
    public Category getCategoryByID(@PathVariable Long categoryID){
        return categoryService
                .getCategoryByID(categoryID)
                .orElseThrow(() -> new CategoryNotExistedException(categoryID));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void createCategory(@RequestBody Category category){

        if (!categoryService.createCategory(category)){
            throw new CategoryAlreadyExistedException(category.getName());
        }
    }

    @PutMapping("/{categoryID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Category updateCategory(@PathVariable Long categoryID,
                                  @RequestBody Category category) {
        Category updatedCategory = categoryService.updateCategory(categoryID, category);
        if (updatedCategory == null){
            throw new CategoryNotExistedException(categoryID);
        }
        return category;
    }

    @DeleteMapping("/{categoryID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteCategory(@PathVariable Long categoryID){
        if (!categoryService.deleteCategory(categoryID)){
            throw new CategoryNotExistedException(categoryID);
        };
    }
}
