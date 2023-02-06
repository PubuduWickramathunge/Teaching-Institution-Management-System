package com.isa.Backend.controller;

import com.isa.Backend.model.Student;
import com.isa.Backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;



    @PostMapping("/register/student")

    public ResponseEntity<Student> addStudentDetails(@RequestBody Student student) {
        Student addedStudent = studentService.updateStudent(student.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(addedStudent);
    }


//    @GetMapping("/{studentId}")
//        public ResponseEntity<Student> getStudentDetails(@PathVariable Long studentId) {
//            Student student = studentService.updateStudent(studentId);
//            return ResponseEntity.status(HttpStatus.OK).body(student);
//        }
    }
