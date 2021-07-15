package com.rookie.ecommerce.service;

import com.rookie.ecommerce.DTO.UserDTO;
import com.rookie.ecommerce.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    List<User> getUsers();

    User getUser(Long userid);

    User getUserEmail(String email);

    UserDTO convertToDTO(User user);

    User convertToEntity(UserDTO userDTO);
}
