package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.CollegePanelDto;
import java.util.List;

public interface CollegePanelService {

    CollegePanelDto createCollege(CollegePanelDto dto);

    CollegePanelDto getCollegeByCode(String collegeCode);

    List<CollegePanelDto> getAllColleges();

    CollegePanelDto updateCollege(String collegeCode, CollegePanelDto dto); // ADD THIS

    void deleteCollege(String collegeCode);

	CollegePanelDto login(String collegeCode, String password);
}
