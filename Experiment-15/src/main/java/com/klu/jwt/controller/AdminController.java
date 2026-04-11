package com.klu.jwt.controller;

import com.klu.jwt.dto.EmployeeRequest;
import com.klu.jwt.model.User;
import com.klu.jwt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/admin")
public class AdminController {
    
    @Autowired
    private UserService userService;
    
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword("default123"); // In production, use encoded password
        user.setRole("EMPLOYEE");
        user.setEmail(request.getEmail());
        user.setFullName(request.getFullName());
        
        User savedUser = userService.createUser(user);
        
        Map<String, Object> response = new HashMap<>();
        response.put("message", "Employee added successfully");
        response.put("employee", savedUser);
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        userService.deleteUser(id);
        
        Map<String, String> response = new HashMap<>();
        response.put("message", "Employee deleted successfully");
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/employees")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllEmployees() {
        List<User> employees = userService.getAllUsers();
        return ResponseEntity.ok(employees);
    }
}