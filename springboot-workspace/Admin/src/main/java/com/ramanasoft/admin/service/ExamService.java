package com.ramanasoft.admin.service;

import java.time.LocalDate;
import java.util.List;

import com.ramanasoft.admin.dto.ExamDto;

public interface ExamService {

    ExamDto createExam(ExamDto dto);

    List<ExamDto> getAllExams();
    
    ExamDto getExamById(Long id);

    ExamDto updateExam(Long id, ExamDto dto);

    void deleteExam(Long id);
    
    List<ExamDto> searchExams(
            String name,
            String level,
            LocalDate examDateAfter
    );
}
