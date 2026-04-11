package com.klu.jwt.controller;

import com.klu.jwt.config.JwtService;
import com.klu.jwt.dto.LoginRequest;
import com.klu.jwt.dto.LoginResponse;
import com.klu.jwt.model.User;
import com.klu.jwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private JwtService jwtService;
    
    @Autowired
    private UserRepository userRepository;
    
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            
            UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
            String token = jwtService.generateToken(userDetails);
            
            User user = userRepository.findByUsername(request.getUsername()).get();
            
            return ResponseEntity.ok(new LoginResponse(token, user.getUsername(), user.getRole(), "Login successful"));
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid username or password");
            return ResponseEntity.status(401).body(error);
        }
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Username already exists");
            return ResponseEntity.badRequest().body(error);
        }
        
        user.setRole(user.getRole() != null ? user.getRole() : "EMPLOYEE");
        User savedUser = userRepository.save(user);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "User registered successfully");
        response.put("username", savedUser.getUsername());
        response.put("role", savedUser.getRole());
        return ResponseEntity.ok(response);
    }
}