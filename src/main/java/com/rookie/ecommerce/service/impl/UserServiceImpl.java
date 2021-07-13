package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.exception.UserException.UserNotExistedException;
import com.rookie.ecommerce.repository.UserRepository;
import com.rookie.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUser(Long userid) {
        return userRepository.findById(userid)
                .orElseThrow(() ->new UserNotExistedException(userid));
    }

    public User getUserEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null){
            throw new UserNotExistedException(email);
        }
        return user;
    }


}
