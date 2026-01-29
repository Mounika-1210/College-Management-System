package com.ramanasoft.college.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursePanelDto {

    private Long id;

    @NotBlank(message = "Course name is required")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String courseName;

    @NotNull(message = "Fees is required")
    @Positive(message = "Fees must be a positive number")
    private Double fees;

    @NotBlank(message = "Eligibility is required")
    @Size(max = 200, message = "Eligibility can have maximum 200 characters")
    private String eligibility;

    @NotNull(message = "College ID is required")
    private Long collegePanelId; // to link with CollegePanel
}


