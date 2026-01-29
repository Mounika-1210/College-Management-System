package com.ramanasoft.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.admin.dto.CourseDto;
import com.ramanasoft.admin.service.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    // CREATE ✅
    @PostMapping
    public CourseDto createCourse(
            @Valid @RequestBody CourseDto dto) {
        return courseService.createCourse(dto);
    }

    // GET ALL COURSES ✅
    @GetMapping
    public List<CourseDto> getAllCourses() {
        return courseService.getAllCourses();
    }

    // GET BY ID ✅
    @GetMapping("/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id);
    }

    // GET BY COLLEGE ✅
    @GetMapping("/college/{collegeId}")
    public List<CourseDto> getCoursesByCollege(@PathVariable Long collegeId) {
        return courseService.getCoursesByCollege(collegeId);
    }

    // GET BY CATEGORY ✅
    @GetMapping("/category/{categoryId}")
    public List<CourseDto> getCoursesByCategory(@PathVariable Long categoryId) {
        return courseService.getCoursesByCategory(categoryId);
    }

    // UPDATE ✅
    @PutMapping("/{id}")
    public CourseDto updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseDto dto) {
        return courseService.updateCourse(id, dto);
    }

    // DELETE ✅
    @DeleteMapping("/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return "Course deleted successfully";
    }
    @GetMapping("/search")
    public List<CourseDto> searchCourses(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long collegeId,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) Double minFees,
            @RequestParam(required = false) Double maxFees) {

        return courseService.searchCourses(
                (name != null && name.isEmpty()) ? null : name,
                collegeId,
                categoryId,
                minFees,
                maxFees
        );
    }
}
