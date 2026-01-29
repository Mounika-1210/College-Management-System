package com.ramanasoft.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.admin.dto.CourseCategoryDto;
import com.ramanasoft.admin.model.CourseCategory;
import com.ramanasoft.admin.repository.CourseCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseCategoryServiceImpl implements CourseCategoryService {

    @Autowired
    private CourseCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    @Override
    public CourseCategoryDto createCategory(CourseCategoryDto dto) {
        CourseCategory category = modelMapper.map(dto, CourseCategory.class);
        CourseCategory saved = categoryRepository.save(category);
        return modelMapper.map(saved, CourseCategoryDto.class);
    }

    // GET ALL
    @Override
    public List<CourseCategoryDto> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(cat -> modelMapper.map(cat, CourseCategoryDto.class))
                .collect(Collectors.toList());
    }

    // GET BY ID
    @Override
    public CourseCategoryDto getCategoryById(Long id) {
        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return modelMapper.map(category, CourseCategoryDto.class);
    }

    // UPDATE ✅ FIXED (NO ID ISSUE)
    @Override
    public CourseCategoryDto updateCategory(Long id, CourseCategoryDto dto) {

        CourseCategory category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // ✅ SAFE FIELD UPDATES
        category.setName(dto.getName());
        category.setDescription(dto.getDescription());

        CourseCategory updated = categoryRepository.save(category);
        return modelMapper.map(updated, CourseCategoryDto.class);
    }

    // DELETE
    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (!categoryRepository.existsById(id)) {
            throw new RuntimeException("Category not found");
        }
        categoryRepository.deleteById(id);
    }
    @Override
    public List<CourseCategoryDto> searchCategories(
            String name,
            String description) {

        return categoryRepository.searchCategories(name, description)
                .stream()
                .map(category -> modelMapper.map(category, CourseCategoryDto.class))
                .toList();
    }
}
