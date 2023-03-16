package com.isa.Backend.dto;

import com.isa.Backend.model.Role;
import com.isa.Backend.model.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private Users user;



}