package com.ramanasoft.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.admin.dto.CourseDto;
import com.ramanasoft.admin.model.Course;
import com.ramanasoft.admin.repository.CollegeRepository;
import com.ramanasoft.admin.repository.CourseCategoryRepository;
import com.ramanasoft.admin.repository.CourseRepository;

import jakarta.transaction.Transactional;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private CourseCategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    // ======================
    // CREATE COURSE
    // ======================
    @Override
    public CourseDto createCourse(CourseDto dto) {

        Course course = modelMapper.map(dto, Course.class);

        course.setCollege(
                collegeRepository.findById(dto.getCollegeId())
                        .orElseThrow(() -> new RuntimeException("College not found"))
        );

        course.setCategory(
                categoryRepository.findById(dto.getCategoryId())
                        .orElseThrow(() -> new RuntimeException("Category not found"))
        );

        Course saved = courseRepository.save(course);
        return mapToDto(saved);
    }

    // ======================
    // GET ALL COURSES
    // ======================
    @Override
    public List<CourseDto> getAllCourses() {
        return courseRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // GET COURSE BY ID
    // ======================
    @Override
    public CourseDto getCourseById(Long id) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        return mapToDto(course);
    }

    // ======================
    // UPDATE COURSE (ID SAFE)
    // ======================
    @Override
    public CourseDto updateCourse(Long id, CourseDto dto) {

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        // ✅ MANUAL FIELD UPDATE (NO ID TOUCH)
        if (dto.getName() != null) {
            course.setName(dto.getName());
        }

        if (dto.getDuration() != null) {
            course.setDuration(dto.getDuration());
        }

        if (dto.getFees() != null) {
            course.setFees(dto.getFees());
        }
        if (dto.getEligibility() != null) {
            course.setEligibility(dto.getEligibility());
        }

        // ✅ UPDATE COLLEGE (ONLY IF PROVIDED)
        if (dto.getCollegeId() != null) {
            course.setCollege(
                    collegeRepository.findById(dto.getCollegeId())
                            .orElseThrow(() -> new RuntimeException("College not found"))
            );
        }

        // ✅ UPDATE CATEGORY (ONLY IF PROVIDED)
        if (dto.getCategoryId() != null) {
            course.setCategory(
                    categoryRepository.findById(dto.getCategoryId())
                            .orElseThrow(() -> new RuntimeException("Category not found"))
            );
        }

        Course updated = courseRepository.save(course);
        return modelMapper.map(updated, CourseDto.class);
    }


    // ======================
    // DELETE COURSE
    // ======================
    @Override
    @Transactional
    public void deleteCourse(Long id) {

        if (!courseRepository.existsById(id)) {
            throw new RuntimeException("Course not found");
        }

        courseRepository.deleteById(id);
    }

    // ======================
    // GET COURSES BY COLLEGE
    // ======================
    @Override
    public List<CourseDto> getCoursesByCollege(Long collegeId) {
        return courseRepository.findByCollegeId(collegeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // GET COURSES BY CATEGORY
    // ======================
    @Override
    public List<CourseDto> getCoursesByCategory(Long categoryId) {
        return courseRepository.findByCategoryId(categoryId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // ENTITY → DTO MAPPER
    // ======================
    private CourseDto mapToDto(Course course) {

        CourseDto dto = modelMapper.map(course, CourseDto.class);

        dto.setCollegeId(course.getCollege().getId());
        dto.setCategoryId(course.getCategory().getId());

        return dto;
    }
    @Override
    public List<CourseDto> searchCourses(
            String name,
            Long collegeId,
            Long categoryId,
            Double minFees,
            Double maxFees) {

        return courseRepository.searchCourses(
                        name,
                        collegeId,
                        categoryId,
                        minFees,
                        maxFees
                )
                .stream()
                .map(this::mapToDto)
                .toList();
    }
}
