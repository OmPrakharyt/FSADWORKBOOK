package com.klu.service;

import com.klu.exception.StudentNotFoundException;
import com.klu.model.Student;
import com.klu.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    
    @Autowired
    private StudentRepository studentRepository;
    
    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }
    
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }
    
    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
    }
    
    @Override
    public Student updateStudent(Long id, Student studentDetails) {
        Student student = getStudentById(id);
        student.setFirstName(studentDetails.getFirstName());
        student.setLastName(studentDetails.getLastName());
        student.setEmail(studentDetails.getEmail());
        student.setCourse(studentDetails.getCourse());
        student.setPhoneNumber(studentDetails.getPhoneNumber());
        return studentRepository.save(student);
    }
    
    @Override
    public void deleteStudent(Long id) {
        Student student = getStudentById(id);
        studentRepository.delete(student);
    }
}