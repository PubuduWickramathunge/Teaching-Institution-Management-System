package com.isa.Backend.service;

import com.isa.Backend.exception.ResourceNotFoundException;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class TeacherService {

    private final CourseRepository courseRepository;

    public TeacherService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getCoursesOfTeacher(Long teacherId) {
        return courseRepository.findByTeacherId(teacherId);
    }

    public Course getCourseById(Long teacherId, Long courseId) {
        return courseRepository.findByIdAndTeacherId(courseId, teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
    }

    public Set<Users> getStudentsInCourse(Long teacherId, Long courseId) {
        Course course = courseRepository.findByIdAndTeacherId(courseId, teacherId)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
        return course.getStudents();
    }
}
