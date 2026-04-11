package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.User;
import com.klu.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins="http://localhost:5173")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> getUsers(){
        return service.getUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user){
        return service.addUser(user);
    }
}