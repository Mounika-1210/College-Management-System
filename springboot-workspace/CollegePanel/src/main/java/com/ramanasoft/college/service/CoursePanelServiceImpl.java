package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.CoursePanelDto;
import com.ramanasoft.college.model.CollegePanel;
import com.ramanasoft.college.model.CoursePanel;
import com.ramanasoft.college.repository.CollegePanelRepository;
import com.ramanasoft.college.repository.CoursePanelRepository;
import com.ramanasoft.college.service.CoursePanelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CoursePanelServiceImpl implements CoursePanelService {

    private final CoursePanelRepository coursePanelRepository;
    private final CollegePanelRepository collegePanelRepository;

    @Override
    public CoursePanelDto createCourse(CoursePanelDto dto) {
        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        CoursePanel course = new CoursePanel();
        course.setCourseName(dto.getCourseName());
        course.setFees(dto.getFees());
        course.setEligibility(dto.getEligibility());
        course.setCollegePanel(college);

        CoursePanel saved = coursePanelRepository.save(course);
        dto.setId(saved.getId());
        return dto;
    }

    @Override
    public CoursePanelDto updateCourse(Long id, CoursePanelDto dto) {
        CoursePanel course = coursePanelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        CollegePanel college = collegePanelRepository.findById(dto.getCollegePanelId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        course.setCourseName(dto.getCourseName());
        course.setFees(dto.getFees());
        course.setEligibility(dto.getEligibility());
        course.setCollegePanel(college);

        coursePanelRepository.save(course);
        dto.setId(course.getId());
        return dto;
    }

    @Override
    public void deleteCourse(Long id) {
        coursePanelRepository.deleteById(id);
    }

    @Override
    public CoursePanelDto getCourseById(Long id) {
        CoursePanel course = coursePanelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToDto(course);
    }

    @Override
    public List<CoursePanelDto> getAllCourses() {
        return coursePanelRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CoursePanelDto> getCoursesByCollegeId(Long collegeId) {
        CollegePanel college = collegePanelRepository.findById(collegeId)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return coursePanelRepository.findByCollegePanel(college).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CoursePanelDto mapToDto(CoursePanel course) {
        CoursePanelDto dto = new CoursePanelDto();
        dto.setId(course.getId());
        dto.setCourseName(course.getCourseName());
        dto.setFees(course.getFees());
        dto.setEligibility(course.getEligibility());
        dto.setCollegePanelId(course.getCollegePanel().getId());
        return dto;
    }
}

