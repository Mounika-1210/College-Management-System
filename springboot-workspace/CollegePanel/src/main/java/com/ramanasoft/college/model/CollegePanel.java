package com.ramanasoft.college.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "college_panel")
@Data
public class CollegePanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;   // DB PK

    @Column(unique = true, nullable = false)
    private String collegeCode; // UUID (Business ID)

    private String collegeName;

    private String password;

    @Column(length = 2000)
    private String history;
}