package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.CoursePanelDto;

import java.util.List;

public interface CoursePanelService {

    CoursePanelDto createCourse(CoursePanelDto dto);

    CoursePanelDto updateCourse(Long id, CoursePanelDto dto);

    void deleteCourse(Long id);

    CoursePanelDto getCourseById(Long id);

    List<CoursePanelDto> getAllCourses();

    List<CoursePanelDto> getCoursesByCollegeId(Long collegeId);
}

