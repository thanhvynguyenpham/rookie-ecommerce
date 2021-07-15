package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.DTO.UserDTO;
import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<UserDTO> getUsers(){
        return userService.getUsers().stream().map(userService::convertToDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userid}")
    public UserDTO getUser(@PathVariable Long userid){
        return userService.convertToDTO(userService.getUser(userid));
    }

    @GetMapping("/email/{email}")
    public UserDTO getUserByEmail(@PathVariable String email){

        return userService.convertToDTO(userService.getUserEmail(email));
    }


}
