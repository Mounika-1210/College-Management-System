package com.ramanasoft.college.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramanasoft.college.dto.CollegePanelDto;
import com.ramanasoft.college.dto.LoginRequestDto;
import com.ramanasoft.college.service.CollegePanelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/colleges")
public class CollegePanelController {

    @Autowired
    private CollegePanelService collegePanelService;

    // -------- CREATE --------
    @PostMapping
    public ResponseEntity<CollegePanelDto> createCollege(@Valid @RequestBody CollegePanelDto dto) {
        CollegePanelDto createdCollege = collegePanelService.createCollege(dto);
        return ResponseEntity.ok(createdCollege);
    }

    // -------- UPDATE BY COLLEGE CODE --------
    @PutMapping("/{collegeCode}")
    public ResponseEntity<CollegePanelDto> updateCollege(
            @PathVariable String collegeCode,
            @Valid @RequestBody CollegePanelDto dto) {

        CollegePanelDto updatedCollege = collegePanelService.updateCollege(collegeCode, dto);
        return ResponseEntity.ok(updatedCollege);
    }

    // -------- DELETE BY COLLEGE CODE --------
    @DeleteMapping("/{collegeCode}")
    public ResponseEntity<Void> deleteCollege(@PathVariable String collegeCode) {
        collegePanelService.deleteCollege(collegeCode);
        return ResponseEntity.noContent().build();
    }

    // -------- GET BY COLLEGE CODE --------
    @GetMapping("/{collegeCode}")
    public ResponseEntity<CollegePanelDto> getCollegeByCode(@PathVariable String collegeCode) {
        CollegePanelDto college = collegePanelService.getCollegeByCode(collegeCode);
        return ResponseEntity.ok(college);
    }

    // -------- GET ALL --------
    @GetMapping("/all")
    public ResponseEntity<List<CollegePanelDto>> getAllColleges() {
        List<CollegePanelDto> colleges = collegePanelService.getAllColleges();
        return ResponseEntity.ok(colleges);
    }

    // -------- LOGIN --------
    @PostMapping("/login")
    public ResponseEntity<CollegePanelDto> login(@Valid @RequestBody LoginRequestDto loginRequest) {
        CollegePanelDto college = collegePanelService.login(
                loginRequest.getCollegeCode(),
                loginRequest.getPassword()
        );
        return ResponseEntity.ok(college);
    }
}
