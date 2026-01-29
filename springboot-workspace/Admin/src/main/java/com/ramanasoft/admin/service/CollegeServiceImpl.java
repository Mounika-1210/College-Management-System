package com.ramanasoft.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.admin.dto.CollegeRequestDto;
import com.ramanasoft.admin.dto.CollegeResponseDto;
import com.ramanasoft.admin.model.College;
import com.ramanasoft.admin.repository.CollegeRepository;

import jakarta.transaction.Transactional;

@Service
public class CollegeServiceImpl implements CollegeService {

    @Autowired
    private CollegeRepository collegeRepository;

    @Autowired
    private ModelMapper modelMapper;

    // CREATE
    @Override
    public CollegeResponseDto createCollege(CollegeRequestDto dto) {
        College college = modelMapper.map(dto, College.class);
        College saved = collegeRepository.save(college);
        return modelMapper.map(saved, CollegeResponseDto.class);
    }

    // UPDATE
    @Override
    public CollegeResponseDto updateCollege(Long id, CollegeRequestDto dto) {

        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        // map request DTO to existing entity (keeps ID)
        modelMapper.map(dto, college);

        College updated = collegeRepository.save(college);
        return modelMapper.map(updated, CollegeResponseDto.class);
    }

    // GET BY ID
    @Override
    public CollegeResponseDto getCollegeById(Long id) {
        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return modelMapper.map(college, CollegeResponseDto.class);
    }

    // GET ALL
    @Override
    public List<CollegeResponseDto> getAllColleges() {
        return collegeRepository.findAll()
                .stream()
                .map(college -> modelMapper.map(college, CollegeResponseDto.class))
                .collect(Collectors.toList());
    }

    // DELETE
    @Override
    @Transactional
    public void deleteCollege(Long id) {

        College college = collegeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("College not found"));

        collegeRepository.delete(college);
    }
    
    @Override
    public List<CollegeResponseDto> searchColleges(
            String name,
            String city,
            String state,
            String ranking) {

        return collegeRepository.searchColleges(name, city, state, ranking)
                .stream()
                .map(college -> modelMapper.map(college, CollegeResponseDto.class))
                .toList();
    }
}
