package com.isa.Backend.service;

import com.isa.Backend.model.Student;
import com.isa.Backend.repository.StudentRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final UserRepository userRepository;

    public StudentService(StudentRepository studentRepository, UserRepository userRepository) {
        this.studentRepository = studentRepository;
        this.userRepository = userRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student updateStudent(Student student) {
//        Users user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("User not found with id: " + userId));
//        Student student = new Student();
//        student.setUser(user);
        return studentRepository.save(student);
    }
}

//    public Student updateStudent(Student updatedStudent) {
//        var student = Student.builder()
//                .grade(updatedStudent.getGrade())
//                .dateOfBirth(updatedStudent.getDateOfBirth())
//                .address(updatedStudent.getAddress())
//                .build();
//
//        return studentRepository.save(student);
//
//    }


