package com.ms.ems.dtos;

import java.util.Set;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateEmployeeRequest {
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Email is mandatory")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Role is mandatory")
    private String role;

    @NotBlank(message = "Department is mandatory")
    private String department;

    private Set<String> skills;
    private Set<String> assignedProjects;
}