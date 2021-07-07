package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.service.CategoryService;
import com.rookie.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getProducts(){
        return productService.getProducts();
    }

    @GetMapping("/category/{categoryID}")
    public List<Product> getProductsByCategory(@PathVariable Long categoryID){
        return productService.getProductsByCategory(categoryID);
    }

    @GetMapping("status/{status}")
    public List<Product> getProductsByStatus(@PathVariable String status){
        return productService.getProductsByStatus(status);
    }

    @GetMapping("/{productId}")
    public Product getProductByID(@PathVariable Long productId){
        return productService.getProductByID(productId);
    }

    @PostMapping("/{categoryId}")
    public void addProduct(@RequestBody Product product, @PathVariable Long categoryId){
        productService.addProduct(product, categoryId);
    }

    @PostMapping("/addProducts/{categoryId}")
    public void addProducts(@RequestBody List<Product> products, @PathVariable Long categoryId){
        productService.addProducts(products, categoryId);
    }

    @PutMapping("/updateProduct/{productID}")
    public void updateProduct(
            @PathVariable Long productID,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Long categoryID,
            @RequestParam(required = false) String thumbnail ){
        productService.updateProduct(productID, name, description, categoryID, thumbnail);
    }

    @PutMapping("/updateStatus/{productId}")
    public void updateStatusProduct(@PathVariable Long productId,
                                    @RequestParam String status){
        productService.updateProductStatus(productId, status);
    }

    @DeleteMapping("/deleteProduct/{productId}")
    public void deleteProduct(@PathVariable Long productId){
        productService.deleteProduct(productId);
    }
}
