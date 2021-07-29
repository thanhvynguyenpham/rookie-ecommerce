package com.rookie.ecommerce.exception.ProductException;

public class ProductAlreadyExistedException extends RuntimeException{
    public ProductAlreadyExistedException(String name){
        super("Product with name " + name + " already existed." );
    }
}
