package com.isa.Backend.controller;

import com.isa.Backend.dto.CourseRegRequest;
import com.isa.Backend.dto.CourseResponse;
import com.isa.Backend.model.Course;
import com.isa.Backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses/")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("add-course")
    public ResponseEntity<CourseResponse> createCourses(@RequestBody CourseRegRequest course) {

        Course addedCourse = new Course(course.getName(), course.getDescription(), course.getTeacher());
        CourseResponse courseResponse = CourseResponse.fromCourse(courseService.createCourse(addedCourse));
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
    }

    @GetMapping("all")
    public ResponseEntity<List<CourseResponse>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        List<CourseResponse> courseResponses = courses.stream().map(CourseResponse::fromCourse).collect(Collectors.toList());
        return ResponseEntity.ok(courseResponses);
    }

    @GetMapping("search")
    public ResponseEntity<List<CourseResponse>> searchCourses(@RequestParam("name") String name) {
        List<Course> courses = courseService.searchCoursesByName(name);
        List<CourseResponse> courseResponses = courses.stream().map(CourseResponse::fromCourse).collect(Collectors.toList());
        return ResponseEntity.ok(courseResponses);
    }


}