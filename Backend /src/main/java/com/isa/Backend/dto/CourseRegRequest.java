package com.isa.Backend.dto;

import com.isa.Backend.model.Users;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CourseRegRequest {
    private String name;
    private String description;
    private Users teacher;

}
