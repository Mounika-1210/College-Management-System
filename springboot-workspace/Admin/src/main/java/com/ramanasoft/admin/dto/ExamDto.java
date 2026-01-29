package com.ramanasoft.admin.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@JsonPropertyOrder({"id","name","description","examDate","level"})
public class ExamDto {

    private Long id;

    @NotBlank(message = "Exam name must not be blank")
    @Size(min = 2, max = 100, message = "Exam name must be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Description must not be blank")
    @Size(min = 5, max = 500, message = "Description must be between 5 and 500 characters")
    private String description;

    @NotNull(message = "Exam date must not be null")
    @FutureOrPresent(message = "Exam date must be today or a future date")
    private LocalDate examDate;

    @NotBlank(message = "Level must not be blank")
    @Pattern(
        regexp = "^(National|State|University|International)$",
        message = "Level must be one of: National, State, University, International"
    )
    private String level;
}
