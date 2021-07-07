package com.rookie.ecommerce.exception.CategoryException;

public class NoCategoryException extends RuntimeException{
    public NoCategoryException(String status){
        super("No category with status " + status + " yet");
    }
}
