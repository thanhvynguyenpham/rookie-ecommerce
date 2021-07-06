package com.rookie.ecommerce.exception.CategoryException;

public class CategoryNotExistedException extends RuntimeException{
    public CategoryNotExistedException(Long id){

        super("Could not find category with id = " + id);
    }
}
