package com.klu.auth.service;

import com.klu.auth.dto.*;
import com.klu.auth.exception.UserNotFoundException;
import com.klu.auth.model.User;
import com.klu.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Override
    public User register(RegisterRequest request) {
        // Check if username exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        
        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword()); // In production, encode password
        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setAddress(request.getAddress());
        
        return userRepository.save(user);
    }
    
    @Override
    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
            .orElseThrow(() -> new UserNotFoundException("User not found"));
        
        if (!user.getPassword().equals(request.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        
        return user;
    }
    
    @Override
    public UserResponse getUserProfile(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
        
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getCreatedAt()
        );
    }
    
    @Override
    public UserResponse getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new UserNotFoundException("User not found: " + username));
        
        return new UserResponse(
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getFullName(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getCreatedAt()
        );
    }
}