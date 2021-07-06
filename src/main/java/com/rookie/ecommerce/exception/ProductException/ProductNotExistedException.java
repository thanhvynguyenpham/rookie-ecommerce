package com.rookie.ecommerce.exception.ProductException;

public class ProductNotExistedException extends RuntimeException{
    public ProductNotExistedException(Long id){
        super("Could not find product with id = " + id);
    }
}
