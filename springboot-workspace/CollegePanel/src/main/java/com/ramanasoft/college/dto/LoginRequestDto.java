package com.ramanasoft.college.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {

    @NotBlank(message = "College code is required")
    private String collegeCode;

    @NotBlank(message = "Password is required")
    private String password;
}

