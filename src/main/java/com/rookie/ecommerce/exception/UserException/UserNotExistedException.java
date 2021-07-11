package com.rookie.ecommerce.exception.UserException;

public class UserNotExistedException extends RuntimeException{
    public UserNotExistedException(Long id){
        super("Could not find user with id = " + id);
    }
    public UserNotExistedException(String email){
        super("Could not find user with email = " + email);
    }
}
