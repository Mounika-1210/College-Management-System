package com.ramanasoft.college.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdmissionDto {

    @NotBlank
    private String applicantName;

    @NotBlank
    private String role;

    @NotBlank
    private String qualification;

    @NotBlank
    private String email;

    @NotBlank
    private String collegeId;
}
