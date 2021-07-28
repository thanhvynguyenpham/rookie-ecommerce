package com.rookie.ecommerce.service.impl;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.DTO.UserDTO;
import com.rookie.ecommerce.entity.Product;
import com.rookie.ecommerce.entity.Role;
import com.rookie.ecommerce.entity.RoleName;
import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.exception.UserException.UserNotExistedException;
import com.rookie.ecommerce.repository.RoleRepository;
import com.rookie.ecommerce.repository.UserRepository;
import com.rookie.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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

    public User updateUserStatus(Long userid, String status){
        User user = userRepository.findById(userid).orElseThrow(() -> new UserNotExistedException((userid)));
        user.setStatus(status);
        userRepository.save(user);
        return user;
    }

    public User updateUser(Long userid, User user){
        if (!userRepository.existsById(userid)){
            return null;
        }
        user.setId(userid);
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long userid){
        userRepository.deleteById(userid);
    }


    @Override
    public UserDTO convertToDTO(User user) {
        if (user == null) {
            return null;
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setFullname(user.getFullname());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setAddress(user.getAddress());
        userDTO.setStatus(user.getStatus());
        userDTO.setRoles(user.getRoles().stream().map(Role::toString).collect(Collectors.toSet()));
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
        user.setFullname(userDTO.getFullname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setAddress(userDTO.getAddress());
        user.setStatus(userDTO.getStatus());
        user.setRoles(convertToRoles(userDTO.getRoles()));
        return user;
    }

    public User createUser(User user) {
        user.setStatus("ENABLE");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Set<Role> convertToRoles(Set<String> roles){
        Set<Role> roleSet = new HashSet<>();
        roles.forEach(role -> roleSet
                .add(roleRepository.findByName(RoleName.valueOf(role))
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."))));
        return roleSet;
    }
}
