package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Enrollment;
import com.isa.Backend.model.EnrollmentId;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.EnrollmentRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.EmbeddedId;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class EnrollmentService {

    private final CourseRepository courseRepository;

    private final EnrollmentRepository enrollmentRepository;

    private final UserRepository userRepository;

    public EnrollmentService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
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
