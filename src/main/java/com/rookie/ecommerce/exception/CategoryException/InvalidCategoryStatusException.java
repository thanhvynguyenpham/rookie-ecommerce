package com.rookie.ecommerce.exception.CategoryException;

import com.rookie.ecommerce.entity.Category;
import com.rookie.ecommerce.entity.Product;

public class InvalidCategoryStatusException extends RuntimeException{
    public InvalidCategoryStatusException(String status){
        super(status + " is not a valid status. Category status includes: " + Category.CATEGORY_STATUS);
    }
}
