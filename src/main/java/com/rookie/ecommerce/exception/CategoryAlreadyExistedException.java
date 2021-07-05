package com.rookie.ecommerce.exception;

public class CategoryAlreadyExistedException extends RuntimeException{
    public CategoryAlreadyExistedException(String name){
        super("Category with name = " + name + " already existed");
    }
}
