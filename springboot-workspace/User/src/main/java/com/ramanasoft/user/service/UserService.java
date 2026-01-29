package com.ramanasoft.user.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.user.dto.RequestDto;
import com.ramanasoft.user.dto.ResponseDto;
import com.ramanasoft.user.exception.EmailAlreadyExistsException;
import com.ramanasoft.user.exception.MobileAlreadyExistsException;
import com.ramanasoft.user.exception.UserNotFoundException;
import com.ramanasoft.user.model.User;
import com.ramanasoft.user.repository.UserRepo;
import com.ramanasoft.user.security.JwtUtil;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    private final ModelMapper mapper;
    private final EmailService emailService;
    private final SmsService smsService;
    private final JwtUtil jwtUtil;

    public UserService(UserRepo userRepository, ModelMapper mapper,
                       EmailService emailService, JwtUtil jwtUtil,
                       SmsService smsService) {
        this.mapper = mapper;
        this.emailService = emailService;
        this.jwtUtil = jwtUtil;
        this.smsService = smsService;
    }

    public String generateOtp() {
        return String.valueOf(new Random().nextInt(900000) + 100000);
    }

    // ---------------- REGISTER ----------------
    public RequestDto registerUser(RequestDto dto) {

        if (userRepo.findByEmail(dto.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email is already registered");
        }

        if (userRepo.findByMobileNumber(dto.getMobileNumber()).isPresent()) {
            throw new MobileAlreadyExistsException("Mobile Number is already registered");
        }

        User user = mapper.map(dto, User.class);

        String otp = generateOtp();

        System.out.println("Generated OTP (REGISTER) = " + otp);  // <-- PRINT OTP

        user.setOtp(otp);
        user.setOtpGeneratedAt(LocalDateTime.now());
        user.setOtpVerified(false);

        User saved = userRepo.save(user);

        return mapper.map(saved, RequestDto.class);
    }

    // ---------------- SEND OTP ----------------
    public String sendOtp(String identifier) {

        Optional<User> userOpt = userRepo.findByEmail(identifier);

        if (userOpt.isEmpty()) {
            userOpt = userRepo.findByMobileNumber(identifier);
        }

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOpt.get();
        String otp = generateOtp();

        System.out.println("Generated OTP (SEND OTP) = " + otp);  // <-- PRINT OTP

        user.setOtp(otp);
        user.setOtpGeneratedAt(LocalDateTime.now());
        user.setOtpVerified(false);

        userRepo.save(user);

        return "OTP sent successfully";
    }

    // ---------------- VERIFY OTP ----------------
    public String verifyOtp(String identifier, String enteredOtp) {

        Optional<User> userOpt = userRepo.findByEmail(identifier);

        if (userOpt.isEmpty()) {
            userOpt = userRepo.findByMobileNumber(identifier);
        }

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOpt.get();

        if (user.getOtpGeneratedAt() == null ||
                user.getOtpGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new RuntimeException("OTP expired");
        }

        if (!user.getOtp().equals(enteredOtp)) {
            throw new RuntimeException("Invalid OTP");
        }

        user.setOtpVerified(true);
        userRepo.save(user);

        return "OTP verified successfully";
    }

    // ---------------- LOGIN ----------------
    public ResponseDto loginByOtp(String identifier, String otp) {

        Optional<User> userOpt = userRepo.findByEmail(identifier);

        if (userOpt.isEmpty()) {
            userOpt = userRepo.findByMobileNumber(identifier);
        }

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOpt.get();

        if (!otp.equals(user.getOtp())) {
            throw new RuntimeException("Invalid OTP");
        }

        if (user.getOtpGeneratedAt() == null ||
                user.getOtpGeneratedAt().isBefore(LocalDateTime.now().minusMinutes(5))) {
            throw new RuntimeException("OTP expired");
        }

        String token = jwtUtil.generateToken(
                user.getEmail() != null ? user.getEmail() : user.getMobileNumber()
        );

        ResponseDto response = new ResponseDto();
        response.setEmail(user.getEmail());
        response.setMobileNumber(user.getMobileNumber());
        response.setToken(token);

        return response;
    }

    // ---------------- UPDATE ----------------
    public ResponseDto updateProfile(String identifier, RequestDto dto) {

        Optional<User> userOpt = userRepo.findByEmail(identifier);

        if (userOpt.isEmpty()) {
            userOpt = userRepo.findByMobileNumber(identifier);
        }

        if (userOpt.isEmpty()) {
            throw new UserNotFoundException("User not found");
        }

        User user = userOpt.get();

        if (dto.getName() != null) user.setName(dto.getName());
        if (dto.getMobileNumber() != null) user.setMobileNumber(dto.getMobileNumber());

        User updated = userRepo.save(user);

        ResponseDto res = new ResponseDto();
        res.setEmail(updated.getEmail());
        res.setMobileNumber(updated.getMobileNumber());

        return res;
    }
}
