package com.ms.ems.dtos;

import java.util.Set;

import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
public class UpdateEmployeeRequest {
    private String name;

    @Email(message = "Invalid email format")
    private String email;

    private String role;
    private String department;

    private Set<String> skills;
    private Set<String> assignedProjects;
}