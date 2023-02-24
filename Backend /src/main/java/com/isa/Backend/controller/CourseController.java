package com.isa.Backend.controller;

import com.isa.Backend.dto.CourseRegRequest;
import com.isa.Backend.dto.CourseResponse;
import com.isa.Backend.model.Course;
import com.isa.Backend.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/courses/")
public class CourseController {

    private final CourseService courseService;

    @PostMapping("add-course")
    public ResponseEntity<CourseResponse> createCourses(@RequestBody CourseRegRequest course)  {

        Course addedCourse = new Course(course.getName(), course.getDescription(), course.getTeacher());
        CourseResponse courseResponse = CourseResponse.fromCourse(courseService.createCourse(addedCourse));
        return ResponseEntity.status(HttpStatus.CREATED).body(courseResponse);
    }

}
