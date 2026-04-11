package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.User;
import com.klu.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public List<User> getUsers(){
        return repo.findAll();
    }

    public User addUser(User user){
        return repo.save(user);
    }
}