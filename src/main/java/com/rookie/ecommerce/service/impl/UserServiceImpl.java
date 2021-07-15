package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.DTO.UserDTO;
import com.rookie.ecommerce.entity.Product;
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


    @Override
    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setStatus(user.getStatus());
        return userDTO;
    }

    @Override
    public User convertToEntity(UserDTO userDTO){
        if (userDTO == null){
            return null;
        }
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        user.setFullname(userDTO.getUsername());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setStatus(userDTO.getStatus());
        return user;
    }
}
