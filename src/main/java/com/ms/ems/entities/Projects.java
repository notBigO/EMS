package com.ms.ems.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String description;
}