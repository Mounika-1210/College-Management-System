package com.ramanasoft.user.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.ramanasoft.user.dto.RequestDto;
import com.ramanasoft.user.dto.ResponseDto;
import com.ramanasoft.user.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000") // allow React frontend
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // ✅ Register new user
    @PostMapping("/register")
    public ResponseEntity<RequestDto> registerUser(@Valid @RequestBody RequestDto dto) {
        RequestDto requestDto = service.registerUser(dto);
        return new ResponseEntity<>(requestDto, HttpStatus.CREATED);
    }


    // ✅ Send OTP to email or phone (Frontend sends JSON)
    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, String> request) {
        String identifier = request.get("identifier");
        String otpMsg = service.sendOtp(identifier);
        return new ResponseEntity<>(otpMsg, HttpStatus.OK);
    }

    // ✅ Verify OTP (Frontend sends JSON)
    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody Map<String, String> request) {

        String identifier = request.get("identifier");
        String otp = request.get("otp");

        if (identifier == null || identifier.isBlank()) {
            throw new RuntimeException("Identifier is required");
        }

        if (otp == null || !otp.matches("^[0-9]{6}$")) {
            throw new RuntimeException("OTP must be 6 digits");
        }

        String result = service.verifyOtp(identifier, otp);
        return ResponseEntity.ok(result);
    }


    // ✅ Login using OTP (Frontend sends JSON)
    @PostMapping("/login")
    public ResponseEntity<ResponseDto> loginByOtp(@RequestBody Map<String, String> request) {
        String identifier = request.get("identifier");
        String otp = request.get("otp");
        ResponseDto response = service.loginByOtp(identifier, otp);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // ✅ Update user profile (Frontend sends JSON)
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProfile(
            @RequestBody Map<String, Object> request) {

        String identifier = (String) request.get("identifier");
        RequestDto dto = new RequestDto();
        dto.setName((String) request.get("name"));
        dto.setMobileNumber((String) request.get("mobileNumber"));
        dto.setEmail((String) request.get("email"));

        ResponseDto updated = service.updateProfile(identifier, dto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}