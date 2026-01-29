package com.ramanasoft.admin.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.ramanasoft.college.dto.CollegePanelDto;
 
@FeignClient(name = "CollegePanel")
public interface CollegePanelClient {
 
    @GetMapping("/Colleges/{id}")
    CollegePanelDto getCollege(@PathVariable("id") Long id);
}
