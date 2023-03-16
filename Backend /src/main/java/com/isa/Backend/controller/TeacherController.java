package com.isa.Backend.controller;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/teachers/{teacherId}")
public class TeacherController {

    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/courses")
    public List<Course> getCoursesOfTeacher(@PathVariable Long teacherId) {
        return teacherService.getCoursesOfTeacher(teacherId);
    }

    @GetMapping("/{courseId}")
    public Course getCourseById(@PathVariable Long teacherId, @PathVariable Long courseId) {
        return teacherService.getCourseById(teacherId, courseId);
    }

    @GetMapping("/{courseId}/students")
    public Set<Users> getStudentsInCourse(@PathVariable Long teacherId, @PathVariable Long courseId) {
        return teacherService.getStudentsInCourse(teacherId, courseId);
    }
}
