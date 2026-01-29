package com.ramanasoft.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.admin.dto.CourseCategoryDto;
import com.ramanasoft.admin.service.CourseCategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/categories")
public class CourseCategoryController {

    @Autowired
    private CourseCategoryService categoryService;

    // ✅ CREATE
    @PostMapping
    public CourseCategoryDto createCategory(
            @Valid @RequestBody CourseCategoryDto dto) {
        return categoryService.createCategory(dto);
    }

    // ✅ GET ALL
    @GetMapping
    public List<CourseCategoryDto> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public CourseCategoryDto getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id);
    }

    // ✅ UPDATE
    @PutMapping("/{id}")
    public CourseCategoryDto updateCategory(
            @PathVariable Long id,
            @Valid @RequestBody CourseCategoryDto dto) {
        return categoryService.updateCategory(id, dto);
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "Category deleted successfully";
    }
    @GetMapping("/search")
    public List<CourseCategoryDto> searchCategories(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String description) {

        return categoryService.searchCategories(
                (name != null && name.isEmpty()) ? null : name,
                (description != null && description.isEmpty()) ? null : description
        );
    }

}
