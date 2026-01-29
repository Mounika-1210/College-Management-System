package com.ramanasoft.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.user.dto.RequestDto;
import com.ramanasoft.user.dto.ResponseDto;
import com.ramanasoft.user.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<RequestDto> registerUser(@RequestBody RequestDto dto) {
        return new ResponseEntity<>(service.registerUser(dto), HttpStatus.CREATED);
    }

    @PostMapping("/send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody RequestDto dto) {
        return ResponseEntity.ok(service.sendOtp(dto.getIdentifier()));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<String> verifyOtp(@RequestBody RequestDto dto) {
        return ResponseEntity.ok(service.verifyOtp(dto.getIdentifier(), dto.getOtp()));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody RequestDto dto) {
        return ResponseEntity.ok(service.loginByOtp(dto.getIdentifier(), dto.getOtp()));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDto> update(@RequestBody RequestDto dto) {
        return ResponseEntity.ok(service.updateProfile(dto.getIdentifier(), dto));
    }
}
