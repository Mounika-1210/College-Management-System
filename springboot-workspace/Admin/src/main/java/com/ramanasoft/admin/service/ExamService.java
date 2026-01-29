package com.ramanasoft.admin.service;



import com.ramanasoft.admin.dto.ExamDto;

import java.util.List;

public interface ExamService {

    ExamDto createExam(ExamDto dto);

    List<ExamDto> getAllExams();
    
    ExamDto getExamById(Long id);

    ExamDto updateExam(Long id, ExamDto dto);

    void deleteExam(Long id);
}
