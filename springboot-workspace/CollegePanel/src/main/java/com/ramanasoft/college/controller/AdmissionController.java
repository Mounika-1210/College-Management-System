package com.ramanasoft.college.controller;

import java.util.List;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.college.dto.AdmissionDto;
import com.ramanasoft.college.model.Admission;
import com.ramanasoft.college.service.AdmissionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admissions")
public class AdmissionController {

    private final AdmissionService service;

    public AdmissionController(AdmissionService service) {
        this.service = service;
    }

    // Apply for Admission
    @PostMapping
    public Admission applyAdmission(@Valid @RequestBody AdmissionDto dto) {
        return service.applyAdmission(dto);
    }

    // Get Admission by Admission ID
    @GetMapping("/{admissionId}")
    public Admission getByAdmissionId(@PathVariable Long admissionId) {
        return service.getByAdmissionId(admissionId);
    }

    // Get All Admissions
    @GetMapping
    public List<Admission> getAllAdmissions() {
        return service.getAllAdmissions();
    }

    // Get Admissions by College Code
    @GetMapping("/college/{collegeCode}")
    public List<Admission> getByCollegeId(@PathVariable String collegeCode) {
        return service.getByCollegeId(collegeCode);
    }

    // Update Admission
    @PutMapping("/{admissionId}")
    public Admission updateAdmission(
            @PathVariable Long admissionId,
            @Valid @RequestBody AdmissionDto dto) {
        return service.updateAdmission(admissionId, dto);
    }

    // Delete Admission
    @DeleteMapping("/{admissionId}")
    public String deleteAdmission(@PathVariable Long admissionId) {
        service.deleteAdmission(admissionId);
        return "Admission deleted successfully";
    }
}
