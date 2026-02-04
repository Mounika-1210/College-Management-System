package com.ramanasoft.college.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AdmissionDto {

    @NotBlank(message = "Applicant name is required")
    @Size(min = 2, max = 100, message = "Applicant name must be 2–100 characters")
    private String applicantName;

    @NotBlank(message = "Role is required")
    @Pattern(regexp = "STUDENT|TEACHING|NON_TEACHING",
             message = "Role must be STUDENT, TEACHING or NON_TEACHING")
    private String role;

    @NotBlank(message = "Qualification is required")
    @Size(max = 100, message = "Qualification max 100 characters")
    private String qualification;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "College ID is required")
    @Size(min = 5, max = 50, message = "College ID length invalid")
    private String collegeId;
}

