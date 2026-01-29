package com.ramanasoft.admin.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramanasoft.admin.dto.CollegeRequestDto;
import com.ramanasoft.admin.dto.CollegeResponseDto;
import com.ramanasoft.admin.service.CollegeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/colleges")
public class CollegeController {

    @Autowired
    private CollegeService collegeService;

    // CREATE
    @PostMapping("/add")
    public ResponseEntity<CollegeResponseDto> createCollege(
            @Valid @RequestBody CollegeRequestDto dto) {

        CollegeResponseDto response = collegeService.createCollege(dto);
        return ResponseEntity.ok(response);
    }

    // UPDATE
    @PutMapping("/{id}")
    public CollegeResponseDto updateCollege(
            @PathVariable Long id,
            @RequestBody CollegeRequestDto dto) {
        return collegeService.updateCollege(id, dto);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public CollegeResponseDto getCollege(@PathVariable Long id) {
        return collegeService.getCollegeById(id);
    }

    // GET ALL
    @GetMapping
    public List<CollegeResponseDto> getAllColleges() {
        return collegeService.getAllColleges();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteCollege(@PathVariable Long id) {
        collegeService.deleteCollege(id);
        return "College deleted successfully";
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<CollegeResponseDto>> searchColleges(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String state,
            @RequestParam(required = false) String ranking) {

        List<CollegeResponseDto> colleges = collegeService.searchColleges(
                (name != null && name.isEmpty()) ? null : name,
                (city != null && city.isEmpty()) ? null : city,
                (state != null && state.isEmpty()) ? null : state,
                (ranking != null && ranking.isEmpty()) ? null : ranking
        );

        return ResponseEntity.ok(colleges);
    }

}
