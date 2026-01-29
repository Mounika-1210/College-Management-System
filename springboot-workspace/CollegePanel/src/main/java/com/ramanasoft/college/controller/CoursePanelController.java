package com.ramanasoft.college.controller;

import com.ramanasoft.college.dto.CoursePanelDto;
import com.ramanasoft.college.service.CoursePanelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CoursePanelController {

    @Autowired
    private CoursePanelService coursePanelService;

    // Create a new course
    @PostMapping
    public ResponseEntity<CoursePanelDto> createCourse(@Valid @RequestBody CoursePanelDto dto) {
        return ResponseEntity.ok(coursePanelService.createCourse(dto));
    }

    // Update a course by ID
    @PutMapping("/{id}")
    public ResponseEntity<CoursePanelDto> updateCourse(@PathVariable Long id,
                                                       @Valid @RequestBody CoursePanelDto dto) {
        return ResponseEntity.ok(coursePanelService.updateCourse(id, dto));
    }

    // Delete a course by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        coursePanelService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }

    // Get a course by ID
    @GetMapping("/{id}")
    public ResponseEntity<CoursePanelDto> getCourse(@PathVariable Long id) {
        return ResponseEntity.ok(coursePanelService.getCourseById(id));
    }

    // Get all courses
    @GetMapping
    public ResponseEntity<List<CoursePanelDto>> getAllCourses() {
        return ResponseEntity.ok(coursePanelService.getAllCourses());
    }

    // Get courses by College ID
    @GetMapping("/college/{collegeId}")
    public ResponseEntity<List<CoursePanelDto>> getCoursesByCollege(@PathVariable Long collegeId) {
        return ResponseEntity.ok(coursePanelService.getCoursesByCollegeId(collegeId));
    }
}

