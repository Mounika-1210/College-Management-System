package com.ramanasoft.user.model;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
	
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
		private String name;
		private  String email;
//		@Pattern(regexp="^[0-9]{10}$", message="Mobile number must be 10 digits")
		private String mobileNumber;
		private String otp;
	    private Boolean otpVerified = false;
	    private LocalDateTime otpGeneratedAt;

}

