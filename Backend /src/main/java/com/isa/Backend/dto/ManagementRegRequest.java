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
public class ManagementRegRequest {
    private String position;
    private String mobile;
    private Users user;

}
