package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.FacultyDto;

import java.util.List;

public interface FacultyService {

    FacultyDto createFaculty(FacultyDto dto);

    FacultyDto updateFaculty(Long id, FacultyDto dto);

    void deleteFaculty(Long id);

    FacultyDto getFacultyById(Long id);

    List<FacultyDto> getAllFaculty();

    List<FacultyDto> getFacultyByCollegeId(Long collegeId);
}

