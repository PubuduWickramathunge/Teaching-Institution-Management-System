package com.isa.Backend.controller;

import com.isa.Backend.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TeacherRegRequest {
    private String subject;
    private String dateOfBirth;
    private String mobile;
    private Users user;

}
