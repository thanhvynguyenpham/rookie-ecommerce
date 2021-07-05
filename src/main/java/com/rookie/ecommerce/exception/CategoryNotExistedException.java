package com.rookie.ecommerce.exception;

public class CategoryNotExistedException extends RuntimeException{
    public CategoryNotExistedException(Long id){
        super("Could not find category with id = " + id);
    }
}
