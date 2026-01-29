package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.FacultyDto;
import com.ramanasoft.college.model.CollegePanel;
import com.ramanasoft.college.model.Faculty;
import com.ramanasoft.college.repository.CollegePanelRepository;
import com.ramanasoft.college.repository.FacultyRepository;
import com.ramanasoft.college.service.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final CollegePanelRepository collegePanelRepository;

    @Override
    public FacultyDto createFaculty(FacultyDto dto) {
        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Faculty faculty = new Faculty();
        faculty.setName(dto.getName());
        faculty.setTitle(dto.getTitle());
        faculty.setQualification(dto.getQualification());
        faculty.setResearchArea(dto.getResearchArea());
        faculty.setCollegePanel(college);

        Faculty saved = facultyRepository.save(faculty);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public FacultyDto updateFaculty(Long id, FacultyDto dto) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        faculty.setName(dto.getName());
        faculty.setTitle(dto.getTitle());
        faculty.setQualification(dto.getQualification());
        faculty.setResearchArea(dto.getResearchArea());
        faculty.setCollegePanel(college);

        facultyRepository.save(faculty);
        dto.setId(faculty.getId());
        return dto;
    }

    @Override
    public void deleteFaculty(Long id) {
        facultyRepository.deleteById(id);
    }

    @Override
    public FacultyDto getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found"));
        return mapToDto(faculty);
    }

    @Override
    public List<FacultyDto> getAllFaculty() {
        return facultyRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FacultyDto> getFacultyByCollegeId(Long collegeId) {
        CollegePanel college = collegePanelRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return facultyRepository.findByCollegePanel(college).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private FacultyDto mapToDto(Faculty faculty) {
        FacultyDto dto = new FacultyDto();
        dto.setId(faculty.getId());
        dto.setName(faculty.getName());
        dto.setTitle(faculty.getTitle());
        dto.setQualification(faculty.getQualification());
        dto.setResearchArea(faculty.getResearchArea());
        dto.setCollegePanelId(faculty.getCollegePanel().getId());
        return dto;
    }
}

