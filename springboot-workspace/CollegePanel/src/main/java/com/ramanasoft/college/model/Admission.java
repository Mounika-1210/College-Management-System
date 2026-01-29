package com.ramanasoft.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "admission_panel")
@Data
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String admissionId;

    @NotBlank
    private String applicantName;

    @NotBlank
    private String role; // STUDENT / TEACHING / NON_TEACHING

    @NotBlank
    private String qualification;

    @NotBlank
    private String email;

    @ManyToOne
    @JoinColumn(name = "college_panel_id")
    private CollegePanel collegePanel;
}