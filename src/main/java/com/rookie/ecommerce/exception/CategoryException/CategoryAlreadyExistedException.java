package com.rookie.ecommerce.exception.CategoryException;

public class CategoryAlreadyExistedException extends RuntimeException{
    public CategoryAlreadyExistedException(String name){

        super("Category with name = " + name + " already existed");
    }
}
