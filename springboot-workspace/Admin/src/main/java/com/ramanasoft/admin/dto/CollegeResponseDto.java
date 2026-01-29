package com.ramanasoft.admin.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Data;

@Data
@JsonPropertyOrder({"id", "name", "city", "state"})
public class CollegeResponseDto {

    private Long id;
    private String name;
    private String city;
    private String state;
   
}
