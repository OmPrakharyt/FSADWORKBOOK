package com.klu.student.controller;

import com.klu.student.exception.StudentNotFoundException;
import com.klu.student.model.Student;
import com.klu.student.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/students")
@Tag(name = "Student Management", description = "Endpoints for managing student records")
public class StudentController {
    
    @Autowired
    private StudentService studentService;
    
    @Operation(
        summary = "Create a new student",
        description = "Adds a new student to the database. Email must be unique."
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Student created successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@klu.edu\",\"course\":\"Computer Science\",\"phoneNumber\":\"9876543210\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input (validation failed or duplicate email)",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\":\"Email already exists\"}"
                )
            )
        )
    })
    @PostMapping
    public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return new ResponseEntity<>(createdStudent, HttpStatus.CREATED);
    }
    
    @Operation(
        summary = "Get all students",
        description = "Retrieves a list of all students in the database"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Successfully retrieved all students",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "[{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@klu.edu\",\"course\":\"Computer Science\",\"phoneNumber\":\"9876543210\"}]"
                )
            )
        )
    })
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }
    
    @Operation(
        summary = "Get student by ID",
        description = "Retrieves a specific student using their unique ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student found successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "{\"id\":1,\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john@klu.edu\",\"course\":\"Computer Science\",\"phoneNumber\":\"9876543210\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found with given ID",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\":\"Student not found with id: 999\"}"
                )
            )
        )
    })
    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(
            @Parameter(description = "ID of the student to retrieve", example = "1", required = true)
            @PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }
    
    @Operation(
        summary = "Update an existing student",
        description = "Updates a student's information by their ID"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Student updated successfully",
            content = @Content(
                mediaType = "application/json",
                schema = @Schema(implementation = Student.class),
                examples = @ExampleObject(
                    value = "{\"id\":1,\"firstName\":\"Jane\",\"lastName\":\"Smith\",\"email\":\"jane@klu.edu\",\"course\":\"Engineering\",\"phoneNumber\":\"9876543211\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\":\"Student not found with id: 999\"}"
                )
            )
        ),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid input",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"firstName\":\"First name is required\"}"
                )
            )
        )
    })
    @PutMapping("/{id}")
    public ResponseEntity<Student> updateStudent(
            @Parameter(description = "ID of the student to update", example = "1", required = true)
            @PathVariable Long id,
            @Valid @RequestBody Student student) {
        Student updatedStudent = studentService.updateStudent(id, student);
        return ResponseEntity.ok(updatedStudent);
    }
    
    @Operation(
        summary = "Delete a student",
        description = "Permanently removes a student from the database"
    )
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "204",
            description = "Student deleted successfully (no content returned)"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Student not found",
            content = @Content(
                mediaType = "application/json",
                examples = @ExampleObject(
                    value = "{\"error\":\"Student not found with id: 999\"}"
                )
            )
        )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(
            @Parameter(description = "ID of the student to delete", example = "1", required = true)
            @PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}