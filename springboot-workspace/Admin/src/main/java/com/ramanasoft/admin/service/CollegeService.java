package com.ramanasoft.admin.service;

import com.ramanasoft.admin.dto.CollegeRequestDto;
import com.ramanasoft.admin.dto.CollegeResponseDto;

import java.util.List;

public interface CollegeService {

    CollegeResponseDto createCollege(CollegeRequestDto dto);

    CollegeResponseDto updateCollege(Long id, CollegeRequestDto dto);

    CollegeResponseDto getCollegeById(Long id);

    List<CollegeResponseDto> getAllColleges();

    void deleteCollege(Long id);
    
    List<CollegeResponseDto> searchColleges(
            String name,
            String city,
            String state,
            String ranking
    );
}

