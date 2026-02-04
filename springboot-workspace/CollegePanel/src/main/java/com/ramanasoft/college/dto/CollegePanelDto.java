package com.ramanasoft.college.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CollegePanelDto {

    private Long id;

    @NotBlank(message = "College code is required")
    @Size(min = 5, max = 50)
    private String collegeCode;

    @NotBlank(message = "College name is required")
    @Size(min = 3, max = 150)
    private String collegeName;

    @Size(max = 2000, message = "History max 2000 characters")
    private String history;
}
