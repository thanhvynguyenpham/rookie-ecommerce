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

    User updateUserStatus(Long userid, String status);

    User updateUser(Long userid, User user);

    void deleteUser(Long userid);

    UserDTO convertToDTO(User user);

    User convertToEntity(UserDTO userDTO);

    User createUser(User user);
}
