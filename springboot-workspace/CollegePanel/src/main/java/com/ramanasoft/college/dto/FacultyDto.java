package com.ramanasoft.college.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDto {

    private Long id;

    @NotBlank(message = "Faculty name is required")
    @Size(min = 2, max = 100, message = "Faculty name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title can have maximum 50 characters")
    private String title;

    @NotBlank(message = "Qualification is required")
    @Size(max = 100, message = "Qualification can have maximum 100 characters")
    private String qualification;

    @NotBlank(message = "Research area is required")
    @Size(max = 200, message = "Research area can have maximum 200 characters")
    private String researchArea;

    @NotNull(message = "College ID is required")
    private Long collegePanelId; // to link with CollegePanel
}

