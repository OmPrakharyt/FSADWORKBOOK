package com.klu.jwt.controller;

import com.klu.jwt.config.JwtService;
import com.klu.jwt.model.User;
import com.klu.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private JwtService jwtService;
    
    @GetMapping("/profile")
    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    public ResponseEntity<?> getProfile(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = authHeader.substring(7);
        String username = jwtService.extractUsername(token);
        
        User user = userService.getUserByUsername(username);
        
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        
        // Return only non-sensitive information
        java.util.Map<String, Object> profile = new java.util.HashMap<>();
        profile.put("username", user.getUsername());
        profile.put("email", user.getEmail());
        profile.put("fullName", user.getFullName());
        profile.put("role", user.getRole());
        
        return ResponseEntity.ok(profile);
    }
}