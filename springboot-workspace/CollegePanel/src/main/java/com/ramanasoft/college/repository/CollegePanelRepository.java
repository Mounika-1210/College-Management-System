package com.ramanasoft.college.repository;

import com.ramanasoft.college.model.CollegePanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CollegePanelRepository extends JpaRepository<CollegePanel, Long> {

    // Find a college panel by college code
    Optional<CollegePanel> findByCollegeCode(String collegeCode);

    // Check if a college exists by college code
    boolean existsByCollegeCode(String collegeCode);

    // Optional: Find a college by college code and password (for login purposes)
    Optional<CollegePanel> findByCollegeCodeAndPassword(String collegeCode, String password);

    // Optional: Delete a college panel by college code
    void deleteByCollegeCode(String collegeCode);
    
}

