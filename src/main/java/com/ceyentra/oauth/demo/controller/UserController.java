package com.ceyentra.oauth.demo.controller;

import com.ceyentra.oauth.demo.entity.User;
import com.ceyentra.oauth.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value="/user")
    public List<User> listUser(){
        return userService.findAll();
    }

    @PostMapping(value = "/user")
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping("/user/{id}")
    public User updateUser(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping(value = "/user/{id}")
    public String delete(@PathVariable(value = "id") Long id){
        userService.delete(id);
        return "User Deleted Successfully!";
    }

}
