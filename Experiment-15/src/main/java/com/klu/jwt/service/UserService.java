package com.klu.jwt.service;

import com.klu.jwt.model.User;
import com.klu.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }
    
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}