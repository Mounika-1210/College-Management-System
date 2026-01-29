package com.ramanasoft.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private Long id;

    @NotNull(message = "collegeId must not be null")
    @JsonProperty("collegeId")
    private Long collegeId;

    @NotBlank(message = "User name must not be blank")
    @Size(min = 2, max = 50, message = "User name must be between 2 and 50 characters")
    private String userName;

    @NotNull(message = "Rating must not be null")
    @DecimalMin(value = "1.0", message = "Rating must be at least 1.0")
    @DecimalMax(value = "5.0", message = "Rating must not exceed 5.0")
    private Double rating;

    @NotBlank(message = "Comment must not be blank")
    @Size(min = 5, max = 500, message = "Comment must be between 5 and 500 characters")
    private String comment;
}
