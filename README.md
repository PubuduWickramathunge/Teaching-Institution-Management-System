
# Teaching Institution Management System

This is a web-based application built to handle all aspects of the learning, teaching and managing process of an educational institution.

## Technology Stack
  ### Backend:

  - Spring Boot: version v2.7.8 used as the main framework for the backend development.
  - Spring Security: for authentication and authorization.
  - Gradle: as the build tool.

### Frontend:

  - React: version v18.2.0 used as the main framework for the frontend development.
  - Ant Design: version 5.1.7 used as a UI component library.

### Database:

  - MySQL: version 8.0.32 used as the database management system.

### Deployment:

  - Docker: version 23.0.1 used to fully containerize the application.

### Testing:

  - JUnit 5: used as the testing framework.
  - Mockito 3: used for mocking dependencies.

## Architecture

The application follows the Model-View-Controller (MVC) architecture pattern.


## Main Functionalities

 - Users: Login, Logout, Register
 - Student: View enrolled courses, search courses, enroll and unenroll from courses...
 - Teacher: View assigned classes, view students, provide materials.
 - Manager: view reports on all user and course data, add managers, add courses and assign teachers to courses.

## Setup and Deployment

To set up the application, follow these steps:

    1. Clone the repository from Github.
    2. Set up the database connection in the application.properties file.
    3. Build the backend application using gradle.
    4. Build docker images.
    5. Run the application using Docker: docker-compose up.

Once the application is running, you can access it by navigating to http://localhost:3011 in your web browser.

## Sample UIs
![Register Page](https://github.com/PubuduWickramathunge/Teaching-Institution-Management-System/blob/main/Backend%20/Images/register.png)
![Student Dashboard](https://github.com/PubuduWickramathunge/Teaching-Institution-Management-System/blob/main/Backend%20/Images/Student%20Dashboard.png)
![Admin Dashboard](https://github.com/PubuduWickramathunge/Teaching-Institution-Management-System/blob/main/Backend%20/Images/all%20courses%20of%20teachers.png)

## Conclusion

The Teaching Student Management System is an efficient and effective application that provides a comprehensive solution for managing students, teachers, and courses. It is built using modern and reliable technologies and follows the MVC architecture pattern, making it easy to maintain and extend. With its user-friendly interface and functionalities, this application is suitable for any educational institution.
