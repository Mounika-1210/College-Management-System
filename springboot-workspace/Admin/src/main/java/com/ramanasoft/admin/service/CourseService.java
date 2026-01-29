package com.ramanasoft.admin.service;

import com.ramanasoft.admin.dto.CourseDto;

import java.util.List;

public interface CourseService {

    CourseDto createCourse(CourseDto dto);
    
    List<CourseDto> getAllCourses();
    
    CourseDto getCourseById(Long id);

    List<CourseDto> getCoursesByCollege(Long collegeId);

    List<CourseDto> getCoursesByCategory(Long categoryId);
    
    CourseDto updateCourse(Long id, CourseDto dto);

    void deleteCourse(Long id);
    
    List<CourseDto> searchCourses(
            String name,
            Long collegeId,
            Long categoryId,
            Double minFees,
            Double maxFees
    );
}
