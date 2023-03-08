package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

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
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Users> studentOptional = userRepository.findByEmail(studentName);

        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            Course course = courseOptional.get();
            Users student = studentOptional.get();
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
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Users> studentOptional = userRepository.findByEmail(studentName);

        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            Course course = courseOptional.get();
            Users student = studentOptional.get();
            Set<Course> courses = student.getCourses();
            courses.remove(course);
            student.setCourses(courses);
            userRepository.save(student);
        }
    }


}
