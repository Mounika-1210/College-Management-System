package com.ramanasoft.college.repository;

import com.ramanasoft.college.model.Faculty;
import com.ramanasoft.college.model.CollegePanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FacultyRepository extends JpaRepository<Faculty, Long> {

    // Find all faculty members for a specific college
    List<Faculty> findByCollegePanel(CollegePanel collegePanel);

    // Optional: Find a faculty member by name within a college
    Optional<Faculty> findByNameAndCollegePanel(String name, CollegePanel collegePanel);

    // Optional: Delete all faculty members of a specific college
    void deleteByCollegePanel(CollegePanel collegePanel);
}

