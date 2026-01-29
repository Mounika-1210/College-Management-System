package com.ramanasoft.college.controller;

import com.ramanasoft.college.dto.FacultyDto;
import com.ramanasoft.college.service.FacultyService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/faculty")
public class FacultyController {

    @Autowired
    private FacultyService facultyService;

    // Create a new faculty
    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@Valid @RequestBody FacultyDto dto) {
        return ResponseEntity.ok(facultyService.createFaculty(dto));
    }

    // Update faculty by ID
    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable Long id,
                                                    @Valid @RequestBody FacultyDto dto) {
        return ResponseEntity.ok(facultyService.updateFaculty(id, dto));
    }

    // Delete faculty by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }

    // Get faculty by ID
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFaculty(@PathVariable Long id) {
        return ResponseEntity.ok(facultyService.getFacultyById(id));
    }

    // Get all faculty
    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }

    // Get all faculty for a specific college
    @GetMapping("/college/{collegeId}")
    public ResponseEntity<List<FacultyDto>> getFacultyByCollege(@PathVariable Long collegeId) {
        return ResponseEntity.ok(facultyService.getFacultyByCollegeId(collegeId));
    }
}

