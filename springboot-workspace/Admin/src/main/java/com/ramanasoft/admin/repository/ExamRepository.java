package com.ramanasoft.admin.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ramanasoft.admin.model.Exam;

@Repository
public interface ExamRepository extends JpaRepository<Exam, Long> {

    // Find exams by level
    List<Exam> findByLevel(String level);

    // Find exams after a specific date
    List<Exam> findByExamDateAfter(LocalDate date);

    // Find exam by name
    Exam findByName(String name);
    
    @Query("""
            SELECT e FROM Exam e WHERE
            (:name IS NULL OR LOWER(e.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND
            (:level IS NULL OR e.level = :level) AND
            (:examDateAfter IS NULL OR e.examDate >= :examDateAfter)
        """)
        List<Exam> searchExams(
                @Param("name") String name,
                @Param("level") String level,
                @Param("examDateAfter") LocalDate examDateAfter
        );
}

