package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Category;
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
        return categoryService.getCategoriesByStatus(status);
    }

    @GetMapping("/{categoryID}")
    public Category getCategoryByID(@PathVariable Long categoryID){
        return categoryService.getCategoryByID(categoryID);
    }

    @PostMapping
    public void createCategory(@RequestBody Category category){
        categoryService.createCategory(category);
    }

    @PutMapping("/{categoryID}")
    public void updateCategory(
            @PathVariable Long categoryID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) String status) {
        categoryService.updateCategory(categoryID, name, description, status);
    }

    @DeleteMapping("/{categoryID}")
    public void deleteCategory(@PathVariable Long categoryID){

        categoryService.deleteCategory(categoryID);
    }
}
