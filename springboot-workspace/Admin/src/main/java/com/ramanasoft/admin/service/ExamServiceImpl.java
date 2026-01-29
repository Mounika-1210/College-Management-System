package com.ramanasoft.admin.service;

import com.ramanasoft.admin.dto.ExamDto;
import com.ramanasoft.admin.model.Exam;
import com.ramanasoft.admin.repository.ExamRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ExamRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    // ======================
    // CREATE
    // ======================
    @Override
    public ExamDto createExam(ExamDto dto) {
        Exam exam = modelMapper.map(dto, Exam.class);
        Exam saved = repository.save(exam);
        return modelMapper.map(saved, ExamDto.class);
    }

    // ======================
    // GET ALL
    // ======================
    @Override
    public List<ExamDto> getAllExams() {
        return repository.findAll()
                .stream()
                .map(exam -> modelMapper.map(exam, ExamDto.class))
                .collect(Collectors.toList());
    }

    // ======================
    // GET BY ID
    // ======================
    @Override
    public ExamDto getExamById(Long id) {
        Exam exam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));
        return modelMapper.map(exam, ExamDto.class);
    }

    // ======================
    // UPDATE (ID SAFE)
    // ======================
    @Override
    public ExamDto updateExam(Long id, ExamDto dto) {

        Exam exam = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Exam not found with id: " + id));

        // ❗ DO NOT overwrite ID
        modelMapper.map(dto, exam);

        Exam updated = repository.save(exam);
        return modelMapper.map(updated, ExamDto.class);
    }

    // ======================
    // DELETE
    // ======================
    @Override
    @Transactional
    public void deleteExam(Long id) {

        if (!repository.existsById(id)) {
            throw new RuntimeException("Exam not found with id: " + id);
        }

        repository.deleteById(id);
    }
}
