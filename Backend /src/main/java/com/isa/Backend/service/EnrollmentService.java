package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public EnrollmentService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public void enrollStudent(Long courseId, String studentName) throws Exception {
        Course course = getCourseById(courseId);
        Users student = userRepository.findByEmail(studentName).orElse(null);

        if (course != null && student != null) {
            Set<Course> courses = student.getCourses();

            if (courses.contains(course)) {
                throw new Exception("You have already enrolled in the course.");
            }

            courses.add(course);
            student.setCourses(courses);
            userRepository.save(student);
        }
    }

    public void unenrollStudent(Long courseId, String studentName) {
        Course course = getCourseById(courseId);
        Users student = userRepository.findByEmail(studentName).orElse(null);

        if (course != null && student != null) {
            Set<Course> courses = student.getCourses();
            courses.remove(course);
            student.setCourses(courses);
            userRepository.save(student);
        }
    }

    public Set<Course> getEnrolledCourses(Long studentId) {
        Users student = userRepository.findById(studentId).orElse(null);
        if (student != null) {
            return student.getCourses();
        }
        return Collections.emptySet();
    }

    public Course getCourseById(Long courseId) {
        return courseRepository.findById(courseId).orElse(null);
    }
}
