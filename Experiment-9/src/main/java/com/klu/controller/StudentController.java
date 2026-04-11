package com.klu.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Student;
import com.klu.repositry.StudentRepository;
import com.klu.exception.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository repo;

    // Add Student
    @PostMapping
    public Student addStudent(@RequestBody Student student){

        if(student.getId()<=0){
            throw new InvalidInputException("Student ID must be positive");
        }

        return repo.save(student);
    }

    // Get Student
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id){

        if(id<=0){
            throw new InvalidInputException("Student ID must be positive");
        }

        return repo.findById(id)
                .orElseThrow(()->new StudentNotFoundException(
                        "Student with ID "+id+" not found"));
    }

    // View All Students
    @GetMapping
    public List<Student> getAllStudents(){
        return repo.findAll();
    }

    // Delete Student
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable int id){

        repo.deleteById(id);
        return "Student deleted successfully";
    }
}