package com.ramanasoft.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "admission_panel")
@Data
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Admission ID is required")
    private String admissionId;

    @NotBlank(message = "Applicant name is required")
    @Size(min = 3, max = 50)
    private String applicantName;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "STUDENT|TEACHING|NON_TEACHING",
             message = "Role must be STUDENT / TEACHING / NON_TEACHING")
    private String role;

    @NotBlank(message = "Qualification is required")
    private String qualification;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    private String email;

    @NotNull(message = "College is required")
    @ManyToOne
    @JoinColumn(name = "college_panel_id")
    private CollegePanel collegePanel;
}
