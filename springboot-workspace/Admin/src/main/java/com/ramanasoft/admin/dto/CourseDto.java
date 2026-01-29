package com.ramanasoft.admin.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "duration", "fees", "eligibility", "collegeId", "categoryId"})
public class CourseDto {

    private Long id;

    @NotBlank(message = "Course name must not be blank")
    @Size(min = 2, max = 100, message = "Course name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Duration must not be blank")
    @Pattern(
        regexp = "^[0-9]+(\\.[0-9]+)?\\s*(year|years|month|months)$",
        message = "Duration must be like '3 years' or '6 months'"
    )
    private String duration;

    @NotNull(message = "Fees must not be null")
    @Positive(message = "Fees must be a positive value")
    private Double fees;

    @NotBlank(message = "Eligibility must not be blank")
    @Size(min = 3, max = 255, message = "Eligibility must be between 3 and 255 characters")
    private String eligibility;

    @NotNull(message = "CategoryId must not be null")
    @Positive(message = "CategoryId must be a valid id")
    private Long categoryId;

    @NotNull(message = "CollegeId must not be null")
    @Positive(message = "CollegeId must be a valid id")
    private Long collegeId;
}
