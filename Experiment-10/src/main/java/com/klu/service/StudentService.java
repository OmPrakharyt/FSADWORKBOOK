package com.klu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Student;
import com.klu.repository.StudentRepository;

@Service
public class StudentService {

    @Autowired
    StudentRepository repo;

    public List<Student> getAllStudents(){
        return repo.findAll();
    }

    public Student addStudent(Student student){
        return repo.save(student);
    }

    public void deleteStudent(int id){
        repo.deleteById(id);
    }
}