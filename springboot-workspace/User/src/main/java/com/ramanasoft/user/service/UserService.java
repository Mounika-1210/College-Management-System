package com.ramanasoft.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ramanasoft.user.dto.RequestDto;
import com.ramanasoft.user.dto.ResponseDto;
import com.ramanasoft.user.exception.UserNotFoundException;
import com.ramanasoft.user.model.User;
import com.ramanasoft.user.repository.UserRepo;
import com.ramanasoft.user.security.JwtUtil;

@Service
public class UserService {

    private final UserRepo userRepo;
    private final ModelMapper mapper;
    private final EmailService emailService;
    private final SmsService smsService;
    private final JwtUtil jwtUtil;

    public UserService(UserRepo userRepo,
                       ModelMapper mapper,
                       EmailService emailService,
                       JwtUtil jwtUtil,
                       SmsService smsService) {

        this.userRepo = userRepo;          // ✅ FIX
        this.mapper = mapper;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
        this.smsService = smsService;
    }

		
		  public String generateOtp() {
		        Random random = new Random();
		        int otp = random.nextInt(900000) + 100000;
		        return String.valueOf(otp);
		    }
		
		public RequestDto registerUser(RequestDto dto) {
			Optional<User>email= userRepo.findByEmail(dto.getEmail());
			if(email.isPresent()) {
					throw new UserNotFoundException("Email is already registered");
			}
			Optional<User>number=userRepo.findByMobileNumber(dto.getMobileNumber());
			if(number.isPresent()) {
				throw new UserNotFoundException("Mobile Number is already registerd");
			}
			
			User user =mapper.map(dto, User.class);
			String otp=generateOtp();
			user.setOtp(otp);
			user.setOtpVerified(false);
			user.setOtpGeneratedAt(LocalDateTime.now());
			
			User saved=userRepo.save(user);
//			emailService.sendEmailOtp(user.getEmail(), otp);
//			smsSevice.sendOtpSms(user.getMobileNumber(), otp);
			return mapper.map(saved, RequestDto.class);
		}
		
		public String sendOtp(String identifier) {
			Optional<User>userOtp=userRepo.findByEmail(identifier);
			if(userOtp.isEmpty()) {
				userOtp=userRepo.findByMobileNumber(identifier);
			}
			if(userOtp.isEmpty()) {
				throw new UserNotFoundException("User not found with given email or mobile number");
			}
			User user=userOtp.get();
			String otp=generateOtp();
			user.setOtp(otp);
			user.setOtpGeneratedAt(LocalDateTime.now());
			user.setOtpVerified(false);
		    userRepo.save(user);
//			smsSevice.sendOtpSms(user.getMobileNumber(), otp);
//			emailService.sendEmailOtp(user.getEmail(), otp);
			return " Otp send successfully";
			
		}
		
		 public String verifyOtp(String identifier, String enteredOtp) {
		        Optional<User> userOpt = userRepo.findByEmail(identifier);
		        if (userOpt.isEmpty()) {
		            userOpt = userRepo.findByMobileNumber(identifier);
		        }

		        if (userOpt.isEmpty()) {
		            throw new UserNotFoundException("User not found with given email or mobile number");
		        }
		        
		        User user = userOpt.get();
		        if (user.getOtpGeneratedAt() == null ||
		        	    user.getOtpGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
		        	    throw new RuntimeException("OTP has expired. Please request a new one.");
		        	}
		        if (user.getOtp() != null && user.getOtp().equals(enteredOtp)) {
		            user.setOtpVerified(true);
//		            user.setOtp(null); 
		            userRepo.save(user);
		            return "OTP verified successfully!";
		        } else {
		            throw new UserNotFoundException("Invalid OTP. Please try again.");
		        }
		    }
		 
		public ResponseDto loginByOtp(String identifier, String otp) {
		    Optional<User> userOpt = userRepo.findByEmail(identifier);
		    if (userOpt.isEmpty()) {
		        userOpt = userRepo.findByMobileNumber(identifier);
		    }
		    if (userOpt.isEmpty()) {
		        throw new UserNotFoundException("User not found with identifier: " + identifier);
		    }

		    User user = userOpt.get();
		    if (user.getOtp() == null || !user.getOtp().equals(otp)) {
		        throw new RuntimeException("Invalid OTP!");
		    }
		    if (user.getOtpGeneratedAt() == null ||
		        user.getOtpGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
		        throw new RuntimeException("OTP has expired. Please request a new one.");
		    }
//		    user.setOtpVerified(true);
//		    user.setOtp(null); 
		    userRepo.save(user);
		    String token = jwtUtil.generateToken(
		            user.getEmail() != null ? user.getEmail() : user.getMobileNumber()
		        );
		    ResponseDto response = new ResponseDto();
		    response.setEmail(user.getEmail());
		    response.setMobileNumber(user.getMobileNumber());
		    response.setToken(token);

		    return response;
		}

		public ResponseDto updateProfile(String identifier , RequestDto dto) {
			Optional<User> userOpt = userRepo.findByEmail(identifier);
		    if (userOpt.isEmpty()) {
		        userOpt = userRepo.findByMobileNumber(identifier);
		    }
		    if (userOpt.isEmpty()) {
		        throw new UserNotFoundException("User not found");
		    }
		    User user=userOpt.get();
		    if (dto.getName() != null) user.setName(dto.getName());
		    if (dto.getMobileNumber() != null) user.setMobileNumber(dto.getMobileNumber());
		    
		    User updatedUser=userRepo.save(user);
		    ResponseDto response = new ResponseDto();
		    response.setEmail(updatedUser.getEmail());
		    response.setMobileNumber(updatedUser.getMobileNumber());
		    return response;
		}
}
