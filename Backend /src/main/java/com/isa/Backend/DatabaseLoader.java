package com.isa.Backend;

import com.isa.Backend.dto.RegisterRequest;
import com.isa.Backend.model.Course;
import com.isa.Backend.model.Role;
import com.isa.Backend.service.AuthenticationService;
import com.isa.Backend.service.CourseService;
import com.isa.Backend.service.EnrollmentService;
import com.isa.Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DatabaseLoader {
    private final AuthenticationService authenticationService;
    private final CourseService courseService;
    private final UserService userService;
    private final EnrollmentService enrollmentService;

    @PostConstruct
    public void loadSampleData() throws Exception {
        authenticationService.register(
                new RegisterRequest(
                        "Pubudu",
                        "Wickramathunge",
                        "pwick@gmail.com",
                        "Zxcvbnm99",
                        Role.MANAGEMENT
                )
        );
        RegisterRequest user1 = new RegisterRequest(
                "Kamal",
                "Perera",
                "kamal.perera@gmail.com",
                "123456",
                Role.STUDENT
        );

        authenticationService.register(user1);

        RegisterRequest user2 = new RegisterRequest(
                "Nimal",
                "Fernando",
                "nimal.fernando@gmail.com",
                "654321",
                Role.STUDENT
        );

        authenticationService.register(user2);

        RegisterRequest user3 = new RegisterRequest(
                "Saman",
                "Jayasinghe",
                "saman.jayasinghe@gmail.com",
                "111111",
                Role.STUDENT
        );

        authenticationService.register(user3);

        RegisterRequest user4 = new RegisterRequest(
                "Bimal",
                "Ranasinghe",
                "Bimal.ranasinghe@gmail.com",
                "222222",
                Role.STUDENT
        );

        authenticationService.register(user4);

        RegisterRequest user5 = new RegisterRequest(
                "Sumal",
                "Rajapaksa",
                "Sumal.rajapaksa@gmail.com",
                "333333",
                Role.STUDENT
        );

        authenticationService.register(user5);

        RegisterRequest user6 = new RegisterRequest(
                "Thilina",
                "Madusanka",
                "Thil99@gmail.com",
                "Thil99@gmail.com",
                Role.STUDENT
        );

        authenticationService.register(user6);




        List<RegisterRequest> teachers = Arrays.asList(
                new RegisterRequest("Kusum", "Wickramasinghe", "Kusum.wickramasinghe@gmail.com", "444444", Role.TEACHER),
                new RegisterRequest("Sunil", "Silva", "sunil.silva@gmail.com", "555555", Role.TEACHER),
                new RegisterRequest("ranil", "bandara", "Ranil.bandara@gmail.com", "Osh99@gmail.com", Role.TEACHER),
                new RegisterRequest("Oshada", "Fernando", "Osh99@gmail.com", "Osh99@gmail.com", Role.TEACHER),
                new RegisterRequest("Lakshan", "Dissanayake", "Lakshan.dissanayake@gmail.com", "777777", Role.TEACHER)
        );
        for (RegisterRequest teacher : teachers) {
            authenticationService.register(teacher);
        }



        Course course1 = new Course();
        course1.setName("Mathematics");
        course1.setDescription("This course covers the basic concepts of Mathematics");
        course1.setTeacher(userService.getUserByEmail("Lakshan.dissanayake@gmail.com"));
        courseService.createCourse(course1);

        Course course2 = new Course();
        course2.setName("Discrete Mathematics");
        course2.setDescription("This course covers the fundamental concepts of Discrete Mathematics");
        course2.setTeacher(userService.getUserByEmail("Osh99@gmail.com"));
        courseService.createCourse(course2);

        Course course3 = new Course();
        course3.setName("Calculus");
        course3.setDescription("This course covers the basic concepts of Calculus");
        course3.setTeacher(userService.getUserByEmail("Lakshan.dissanayake@gmail.com"));
        courseService.createCourse(course3);

        Course course4 = new Course();
        course4.setName("Linear Algebra");
        course4.setDescription("This course covers the fundamental concepts of Linear Algebra");
        course4.setTeacher(userService.getUserByEmail("Ranil.bandara@gmail.com"));
        courseService.createCourse(course4);

        Course course5 = new Course();
        course5.setName("Probability Theory");
        course5.setDescription("This course covers the basic concepts of Probability Theory");
        course5.setTeacher(userService.getUserByEmail("Osh99@gmail.com"));
        courseService.createCourse(course5);

        Course course6 = new Course();
        course6.setName("Algorithms and Data Structures");
        course6.setDescription("This course covers the fundamental algorithms and data structures used in computer science");
        course6.setTeacher(userService.getUserByEmail("Sunil.silva@gmail.com"));
        courseService.createCourse(course6);

        Course course7 = new Course();
        course7.setName("Software Engineering Principles");
        course7.setDescription("This course covers the fundamental principles of software engineering");
        course7.setTeacher(userService.getUserByEmail("Sunil.silva@gmail.com"));
        courseService.createCourse(course7);

        Course course8 = new Course();
        course8.setName("Object-Oriented Programming");
        course8.setDescription("This course covers the principles and concepts of object-oriented programming");
        course8.setTeacher(userService.getUserByEmail("Lakshan.dissanayake@gmail.com"));
        courseService.createCourse(course8);

        Course course9 = new Course();
        course9.setName("Software Design and Architecture");
        course9.setDescription("This course covers the principles and concepts of software design and architecture");
        course9.setTeacher(userService.getUserByEmail("Ranil.bandara@gmail.com"));
        courseService.createCourse(course9);

        Course course10 = new Course();
        course10.setName("Database Systems");
        course10.setDescription("This course covers the principles and concepts of database systems");
        course10.setTeacher(userService.getUserByEmail("Osh99@gmail.com"));
        courseService.createCourse(course10);

        Course course11 = new Course();
        course11.setName("Operating Systems");
        course11.setDescription("This course covers the principles and concepts of operating systems");
        course11.setTeacher(userService.getUserByEmail("Osh99@gmail.com"));
        courseService.createCourse(course11);

        Course course12 = new Course();
        course12.setName("Computer Networks");
        course12.setDescription("This course covers the principles and concepts of computer networks");
        course12.setTeacher(userService.getUserByEmail("Sunil.silva@gmail.com"));
        courseService.createCourse(course12);

        Course course13 = new Course();
        course13.setName("Artificial Intelligence");
        course13.setDescription("This course covers the principles and concepts of artificial intelligence");
        course13.setTeacher(userService.getUserByEmail("Osh99@gmail.com"));
        courseService.createCourse(course13);





        enrollmentService.enrollStudent(course13.getId(), user1.getEmail());
        enrollmentService.enrollStudent(course13.getId(), user2.getEmail());
        enrollmentService.enrollStudent(course13.getId(), user5.getEmail());
        enrollmentService.enrollStudent(course13.getId(), user6.getEmail());
        enrollmentService.enrollStudent(course12.getId(), user6.getEmail());
        enrollmentService.enrollStudent(course11.getId(), user2.getEmail());
        enrollmentService.enrollStudent(course10.getId(), user4.getEmail());
        enrollmentService.enrollStudent(course9.getId(), user4.getEmail());
        enrollmentService.enrollStudent(course8.getId(), user5.getEmail());
        enrollmentService.enrollStudent(course7.getId(), user6.getEmail());
        enrollmentService.enrollStudent(course1.getId(), user4.getEmail());
        enrollmentService.enrollStudent(course11.getId(), user6.getEmail());
        enrollmentService.enrollStudent(course6.getId(), user5.getEmail());
        enrollmentService.enrollStudent(course4.getId(), user1.getEmail());
        enrollmentService.enrollStudent(course4.getId(), user4.getEmail());
    }

}