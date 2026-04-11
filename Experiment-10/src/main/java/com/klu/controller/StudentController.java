package com.klu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;

@RestController
@RequestMapping("/students")
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {

    @Autowired
    private StudentRepository repo;

    // Get all students
    @GetMapping
    public List<Student> getStudents() {
        return repo.findAll();
    }

    // Add student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return repo.save(student);
    }

    // Delete student
    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        repo.deleteById(id);
    }
}