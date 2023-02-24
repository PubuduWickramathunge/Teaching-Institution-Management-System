package com.isa.Backend.dto;

import com.isa.Backend.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseResponse {
    private Long id;
    private String name;
    private String description;
    private UserResponse teacher;

    public static CourseResponse fromCourse(Course course) {
        return CourseResponse.builder()
                .id(course.getId())
                .name(course.getName())
                .description(course.getDescription())
                .teacher(UserResponse.fromUser(course.getTeacher()))
                .build();
    }
}
