package com.rookie.ecommerce.exception.ProductException;

import com.rookie.ecommerce.entity.Product;

public class InvalidProductStatusException extends RuntimeException{
    public InvalidProductStatusException(String status){
        super(status + " is not a valid status. Product status includes: " + Product.PRODUCT_STATUS);
    }
}
