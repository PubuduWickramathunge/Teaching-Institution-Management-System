package com.isa.Backend.service;

import com.isa.Backend.model.Course;
import com.isa.Backend.model.Enrollment;
import com.isa.Backend.model.EnrollmentId;
import com.isa.Backend.model.Users;
import com.isa.Backend.repository.CourseRepository;
import com.isa.Backend.repository.EnrollmentRepository;
import com.isa.Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EmbeddedId;
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

    public void enrollStudent(Long courseId, String studentName) {
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Optional<Users> studentOptional = userRepository.findByEmail(studentName);

        if (courseOptional.isPresent() && studentOptional.isPresent()) {
            Course course = courseOptional.get();
            Users student = studentOptional.get();
            Set<Course> courses = student.getCourses();
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

    public Set<Course> getAllCoursesForStudent(String name) {
        Optional<Users> studentOptional = userRepository.findByEmail(name);

        if (studentOptional.isPresent()) {
            Users student = studentOptional.get();
            return student.getCourses();
        }

        return null;
    }

}
