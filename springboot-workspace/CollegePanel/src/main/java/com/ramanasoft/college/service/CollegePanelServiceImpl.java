package com.ramanasoft.college.service;

import com.ramanasoft.college.dto.CollegePanelDto;
import com.ramanasoft.college.model.CollegePanel;
import com.ramanasoft.college.repository.CollegePanelRepository;
import com.ramanasoft.college.utility.TransactionIdGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CollegePanelServiceImpl implements CollegePanelService {

    @Autowired
    private CollegePanelRepository repository;

    @Override
    public CollegePanelDto createCollege(CollegePanelDto dto) {
        if (dto.getCollegeCode() != null && repository.existsByCollegeCode(dto.getCollegeCode())) {
            throw new RuntimeException("College Code already exists");
        }

        CollegePanel entity = mapToEntity(dto);

        // Generate collegeCode using UUID if not provided
        if (entity.getCollegeCode() == null || entity.getCollegeCode().isEmpty()) {
            entity.setCollegeCode("COL" + TransactionIdGenerator.generate());
        }

        return mapToDto(repository.save(entity));
    }

    @Override
    public CollegePanelDto getCollegeByCode(String collegeCode) {
        CollegePanel college = repository.findByCollegeCode(collegeCode)
                .orElseThrow(() -> new RuntimeException("College not found"));
        return mapToDto(college);
    }

    @Override
    public List<CollegePanelDto> getAllColleges() {
        return repository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CollegePanelDto updateCollege(String collegeCode, CollegePanelDto dto) {
        CollegePanel existing = repository.findByCollegeCode(collegeCode)
                .orElseThrow(() -> new RuntimeException("College not found"));

        existing.setCollegeName(dto.getCollegeName());
        existing.setHistory(dto.getHistory());
        

        return mapToDto(repository.save(existing));
    }

    @Override
    public void deleteCollege(String collegeCode) {
        if (!repository.existsByCollegeCode(collegeCode)) {
            throw new RuntimeException("College not found");
        }
        repository.deleteByCollegeCode(collegeCode);
    }

    @Override
    public CollegePanelDto login(String collegeCode, String password) {
        CollegePanel college = repository.findByCollegeCodeAndPassword(collegeCode, password)
                .orElseThrow(() -> new RuntimeException("Invalid Credentials"));
        return mapToDto(college);
    }

    // ---- Helper Methods ----
    private CollegePanel mapToEntity(CollegePanelDto dto) {
        CollegePanel college = new CollegePanel();
        college.setId(dto.getId());
        college.setCollegeCode(dto.getCollegeCode());
        college.setCollegeName(dto.getCollegeName());
        college.setHistory(dto.getHistory());
        
        return college;
    }

    private CollegePanelDto mapToDto(CollegePanel college) {
        CollegePanelDto dto = new CollegePanelDto();
        dto.setId(college.getId());
        dto.setCollegeCode(college.getCollegeCode());
        dto.setCollegeName(college.getCollegeName());
        dto.setHistory(college.getHistory());
        return dto;
    }
}
