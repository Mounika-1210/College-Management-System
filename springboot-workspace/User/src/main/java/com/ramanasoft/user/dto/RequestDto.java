package com.ramanasoft.user.dto;

import lombok.Data;

@Data
public class RequestDto {

    private String name;
    private String email;
    private String mobileNumber;

    private String identifier; // email OR mobile for login/otp
    private String otp; 
}