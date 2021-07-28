package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.DTO.ProductDTO;
import com.rookie.ecommerce.DTO.UserDTO;
import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
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

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO){
        return userService
                .convertToDTO(
                        userService.createUser(
                                userService.convertToEntity(userDTO)));
    }

    @PutMapping("/user/{userid}")
    public UserDTO updateUser(@PathVariable Long userid, @RequestBody UserDTO userDTO){
        return userService
                .convertToDTO(userService
                        .updateUser(userid,userService
                                .convertToEntity(userDTO)));
    }

    @PutMapping("/status/{userid}")
    public UserDTO updateUserStatus(@PathVariable Long userid, @RequestParam String status){
        return userService
                .convertToDTO(userService
                        .updateUserStatus(userid,status));
    }

    @DeleteMapping("user/{userid}")
    public void deleteUser(@PathVariable Long userid){
        userService.deleteUser(userid);
    }

}
