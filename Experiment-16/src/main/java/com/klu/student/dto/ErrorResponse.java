package com.klu.student.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(description = "Error response structure for API error handling")
public class ErrorResponse {
    
    @Schema(description = "Error message", example = "Student not found with id: 999")
    private String message;
    
    @Schema(description = "HTTP status code", example = "404")
    private int statusCode;
    
    @Schema(description = "Timestamp when the error occurred", example = "2026-04-09T10:30:00")
    private LocalDateTime timestamp;
    
    @Schema(description = "Error type/description", example = "Not Found")
    private String error;
    
    @Schema(description = "API path where error occurred", example = "/api/students/999")
    private String path;
    
    // Default Constructor
    public ErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }
    
    // Constructor with message and status code
    public ErrorResponse(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
        this.timestamp = LocalDateTime.now();
    }
    
    // Constructor with all fields
    public ErrorResponse(String message, int statusCode, String error, String path) {
        this.message = message;
        this.statusCode = statusCode;
        this.error = error;
        this.path = path;
        this.timestamp = LocalDateTime.now();
    }
    
    // Getters and Setters
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
    
    public int getStatusCode() {
        return statusCode;
    }
    
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    
    public String getError() {
        return error;
    }
    
    public void setError(String error) {
        this.error = error;
    }
    
    public String getPath() {
        return path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
}