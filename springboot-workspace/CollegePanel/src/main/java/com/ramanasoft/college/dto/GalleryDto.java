package com.ramanasoft.college.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDto {

    private Long id;

    @NotBlank(message = "Media type is required")
    @Pattern(regexp = "IMAGE|VIDEO", message = "Media type must be either IMAGE or VIDEO")
    private String mediaType;

    @NotBlank(message = "Media URL is required")
    @Size(max = 500, message = "Media URL can have maximum 500 characters")
    private String mediaUrl;

    @NotNull(message = "College ID is required")
    private Long collegePanelId; // to link with CollegePanel
}

