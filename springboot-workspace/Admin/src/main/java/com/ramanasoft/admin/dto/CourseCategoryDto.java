package com.ramanasoft.admin.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "description"})
public class CourseCategoryDto {

    private Long id;

    @NotBlank(message = "Category name must not be blank")
    @Size(min = 2, max = 50, message = "Category name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(min = 5, max = 255, message = "Description must be between 5 and 255 characters")
    private String description;
}
