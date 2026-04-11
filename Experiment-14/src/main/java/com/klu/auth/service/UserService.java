package com.klu.auth.service;

import com.klu.auth.dto.*;
import com.klu.auth.model.User;

public interface UserService {
    User register(RegisterRequest request);
    User login(LoginRequest request);
    UserResponse getUserProfile(Long userId);
    UserResponse getUserByUsername(String username);
}