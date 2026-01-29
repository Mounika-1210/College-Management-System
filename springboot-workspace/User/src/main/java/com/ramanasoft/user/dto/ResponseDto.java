package com.ramanasoft.user.dto;

import lombok.Data;

@Data
public class ResponseDto {

    private String email;
    private String mobileNumber;
    private String token;
}