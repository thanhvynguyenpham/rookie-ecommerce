package com.rookie.ecommerce.restcontroller;

import com.rookie.ecommerce.entity.User;
import com.rookie.ecommerce.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping()
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @GetMapping("/{userid}")
    public User getUser(@PathVariable Long userid){
        return userService.getUser(userid);
    }

    @GetMapping("/email/{email}")
    public User getUserByEmail(@PathVariable String email){
        return userService.getUserEmail(email);
    }


}
