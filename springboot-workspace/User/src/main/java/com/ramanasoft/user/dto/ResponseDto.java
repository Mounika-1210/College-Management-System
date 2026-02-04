package com.ramanasoft.user.dto;

import lombok.Data;

@Data
public class ResponseDto {

	private Long id;
    private String email;
    private String mobileNumber;
    private String token;
}