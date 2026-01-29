package com.ramanasoft.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.admin.dto.ExamDto;
import com.ramanasoft.admin.service.ExamService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // ======================
    // CREATE EXAM
    // ======================
    @PostMapping
    public ExamDto createExam(
            @Valid @RequestBody ExamDto dto) {
        return examService.createExam(dto);
    }

    // ======================
    // GET ALL EXAMS
    // ======================
    @GetMapping
    public List<ExamDto> getAllExams() {
        return examService.getAllExams();
    }

    // ======================
    // GET EXAM BY ID
    // ======================
    @GetMapping("/{id}")
    public ExamDto getExamById(@PathVariable Long id) {
        return examService.getExamById(id);
    }

    // ======================
    // UPDATE EXAM
    // ======================
    @PutMapping("/{id}")
    public ExamDto updateExam(
            @PathVariable Long id,
            @Valid @RequestBody ExamDto dto) {
        return examService.updateExam(id, dto);
    }

    // ======================
    // DELETE EXAM
    // ======================
    @DeleteMapping("/{id}")
    public String deleteExam(@PathVariable Long id) {
        examService.deleteExam(id);
        return "Exam deleted successfully with ID: " + id;
    }
}
