package com.isa.Backend.controller;

import com.isa.Backend.dto.StudentRegRequest;
import com.isa.Backend.model.Student;
import com.isa.Backend.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;


    @PostMapping("/register/student")

    public ResponseEntity<Student> addStudentDetails(@RequestBody StudentRegRequest student) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(student.getDateOfBirth());
        Student addedStudent = new Student(student.getGrade(), date, student.getAddress(), student.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.updateStudent(addedStudent));
    }


//    @GetMapping("/{studentId}")
//        public ResponseEntity<Student> getStudentDetails(@PathVariable Long studentId) {
//            Student student = studentService.updateStudent(studentId);
//            return ResponseEntity.status(HttpStatus.OK).body(student);
//        }
}
