package com.isa.Backend.dto;

import com.isa.Backend.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentRegRequest {
    private String grade;
    private String dateOfBirth;
    private String address;
    private Users user;

}


