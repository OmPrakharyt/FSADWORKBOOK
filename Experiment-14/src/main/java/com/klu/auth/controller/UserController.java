package com.klu.auth.controller;

import com.klu.auth.dto.UserResponse;
import com.klu.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/users")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponse> getProfile(@PathVariable Long userId) {
        UserResponse user = userService.getUserProfile(userId);
        return ResponseEntity.ok(user);
    }
    
    @GetMapping("/username/{username}")
    public ResponseEntity<UserResponse> getUserByUsername(@PathVariable String username) {
        UserResponse user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }
}