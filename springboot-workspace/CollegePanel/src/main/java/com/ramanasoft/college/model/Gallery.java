package com.ramanasoft.college.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Entity
@Data
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "IMAGE|VIDEO",
             message = "Media type must be IMAGE or VIDEO")
    private String mediaType;

    @NotBlank
    private String mediaUrl;

    @NotNull
    @ManyToOne
    private CollegePanel collegePanel;
}

