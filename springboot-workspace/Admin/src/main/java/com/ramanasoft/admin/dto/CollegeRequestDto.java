package com.ramanasoft.admin.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class CollegeRequestDto {

    @NotBlank(message = "College name is required")
    @Size(min = 3, max = 100, message = "College name must be between 3 and 100 characters")
    private String name;

    @NotBlank(message = "Description is required")
    @Size(min = 10, message = "Description must be at least 10 characters")
    private String description;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Image URL is required")
    @Pattern(
        regexp = "^(http|https)://.*$",
        message = "Image URL must be a valid URL"
    )
    private String imageUrl;

    @NotBlank(message = "Ranking is required")
    private String ranking;

    @NotBlank(message = "Accreditation is required")
    private String accreditation;

    @NotNull(message = "Highest package is required")
    @Positive(message = "Highest package must be positive")
    private Double highestPackage;

    @NotNull(message = "Fees is required")
    @Positive(message = "Fees must be positive")
    private Double fees;

    @NotBlank(message = "Website URL is required")
    @Pattern(
        regexp = "^(http|https)://.*$",
        message = "Website URL must be a valid URL"
    )
    private String websiteUrl;
}