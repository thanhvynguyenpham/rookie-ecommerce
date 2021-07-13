package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.exception.ProductException.NoProductException;
import com.rookie.ecommerce.exception.ProductException.ProductNotExistedException;
import com.rookie.ecommerce.service.CategoryService;
import com.rookie.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDTO> getProducts(){
        List<Product> products = productService.getProducts();
        return products.stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/category/{categoryID}")
    public List<ProductDTO> getProductsByCategory(@PathVariable Long categoryID){
        List<Product> products = productService.getProductsByCategory(categoryID);
        if (products.isEmpty()){
            throw new NoProductException(categoryID);
        }
        return products.stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("status/{status}")
    public List<ProductDTO> getProductsByStatus(@PathVariable String status){
        List<Product> products = productService.getProductsByStatus(status);
        if (products.isEmpty()){
            throw new NoProductException(status);
        }
        return products.stream()
                .map(productService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDTO getProductByID(@PathVariable Long productId){

        return productService.convertToDTO(productService.getProductByID(productId));
    }

    @PostMapping("/product")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO){

        return productService.convertToDTO(productService
                        .addProduct(productService.convertToEntity(productDTO)));
    }

    @PutMapping("/product/{productID}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ProductDTO updateProduct(
            @PathVariable Long productID,@RequestBody ProductDTO productDTO){
        Product product = productService.convertToEntity(productDTO);
        Product updatedproduct= productService.updateProduct(productID, product);
        if (updatedproduct == null){
            throw new ProductNotExistedException(productID);
        }
        return productService.convertToDTO(product);
    }

    @DeleteMapping("/product/{productId}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteProduct(@PathVariable Long productId){

        productService.deleteProduct(productId);
    }
}
