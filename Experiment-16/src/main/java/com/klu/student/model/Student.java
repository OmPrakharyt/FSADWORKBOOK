package com.klu.student.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "students")
@Schema(description = "Student Entity representing a student in the system")
public class Student {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Unique identifier of the student", example = "1", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;
    
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    @Column(nullable = false)
    @Schema(description = "Student's first name", example = "John", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;
    
    @NotBlank(message = "Last name is required")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    @Column(nullable = false)
    @Schema(description = "Student's last name", example = "Doe", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Column(unique = true, nullable = false)
    @Schema(description = "Student's email address (must be unique)", example = "john.doe@klu.edu", requiredMode = Schema.RequiredMode.REQUIRED)
    private String email;
    
    @NotBlank(message = "Course is required")
    @Column(nullable = false)
    @Schema(description = "Course the student is enrolled in", example = "Computer Science", requiredMode = Schema.RequiredMode.REQUIRED)
    private String course;
    
    @Schema(description = "Student's phone number", example = "+91 9876543210")
    private String phoneNumber;
    
    // Default Constructor
    public Student() {
    }
    
    // Parameterized Constructor
    public Student(Long id, String firstName, String lastName, String email, String course, String phoneNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.course = course;
        this.phoneNumber = phoneNumber;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCourse() {
        return course;
    }
    
    public void setCourse(String course) {
        this.course = course;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}