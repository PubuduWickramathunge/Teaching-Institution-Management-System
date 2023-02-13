package com.isa.Backend.controller;

import com.isa.Backend.dto.TeacherRegRequest;
import com.isa.Backend.model.Teacher;
import com.isa.Backend.service.TeacherService;
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
public class TeacherController {
    private final TeacherService teacherService;


    @PostMapping("/register/teacher")

    public ResponseEntity<Teacher> addTeacherDetails(@RequestBody TeacherRegRequest teacher) throws ParseException {
        Date date = new SimpleDateFormat("dd/MM/yyyy").parse(teacher.getDateOfBirth());
        Teacher addedTeacher = new Teacher(teacher.getSubject(), date, teacher.getMobile(), teacher.getUser());
        return ResponseEntity.status(HttpStatus.CREATED).body(teacherService.updateTeacher(addedTeacher));
    }
}