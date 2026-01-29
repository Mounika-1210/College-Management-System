package com.ramanasoft.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ramanasoft.user.dto.ResponseDto;
 
@FeignClient(name = "USER")
public interface UserClient {
 
    @GetMapping("/users/{id}")
    ResponseDto getUser(@PathVariable("id") Long id);
}
