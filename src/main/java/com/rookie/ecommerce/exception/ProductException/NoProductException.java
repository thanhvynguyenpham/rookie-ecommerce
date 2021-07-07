package com.rookie.ecommerce.exception.ProductException;

public class NoProductException extends RuntimeException{
    public NoProductException(Long categoryId){
        super("No product yet in category with id = " + categoryId);
    }
    public NoProductException(String status){
        super("No product yet with status = " + status);
    }
}
