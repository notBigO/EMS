package com.ms.ems.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
}