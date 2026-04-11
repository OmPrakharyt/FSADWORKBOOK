package com.klu.auth.dto;

import java.time.LocalDateTime;

public class UserResponse {
    private Long id;
    private String username;
    private String email;
    private String fullName;
    private String phoneNumber;
    private String address;
    private LocalDateTime createdAt;
    
    public UserResponse(Long id, String username, String email, String fullName, 
                       String phoneNumber, String address, LocalDateTime createdAt) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.createdAt = createdAt;
    }
    
    // Getters
    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getFullName() { return fullName; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}