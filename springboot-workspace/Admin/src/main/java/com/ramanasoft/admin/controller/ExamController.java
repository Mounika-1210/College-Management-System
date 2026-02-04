package com.ramanasoft.admin.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    @GetMapping("/search")
    public List<ExamDto> searchExams(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String level,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate examDateAfter
    ) {
        return examService.searchExams(
                name,
                level,
                examDateAfter
        );
    }
}
