package com.ramanasoft.college.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "college_panel")
@Data
public class CollegePanel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "College code required")
    @Column(unique = true, nullable = false)
    private String collegeCode;

    @NotBlank(message = "College name required")
    private String collegeName;

    @NotBlank(message = "Password required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @Size(max = 2000)
    private String history;
}
