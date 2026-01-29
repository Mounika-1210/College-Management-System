package com.ramanasoft.admin.service;



import com.ramanasoft.admin.dto.CourseCategoryDto;

import java.util.List;

public interface CourseCategoryService {

    CourseCategoryDto createCategory(CourseCategoryDto dto);

    List<CourseCategoryDto> getAllCategories();

    CourseCategoryDto getCategoryById(Long id);

    CourseCategoryDto updateCategory(Long id, CourseCategoryDto dto);

    void deleteCategory(Long id);
    
    List<CourseCategoryDto> searchCategories(
            String name,
            String description
    );

}
