package com.ramanasoft.admin.repository;



import com.ramanasoft.admin.model.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    // Find exams by level
    List<Exam> findByLevel(String level);

    // Find exams after a specific date
    List<Exam> findByExamDateAfter(LocalDate date);

    // Find exam by name
    Exam findByName(String name);
}

