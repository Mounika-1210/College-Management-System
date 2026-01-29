package com.ramanasoft.college.repository;

import com.ramanasoft.college.model.CoursePanel;
import com.ramanasoft.college.model.CollegePanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursePanelRepository extends JpaRepository<CoursePanel, Long> {

    // Find all courses for a specific college
    List<CoursePanel> findByCollegePanel(CollegePanel collegePanel);

    // Optional: Find a course by its name within a college
    Optional<CoursePanel> findByCourseNameAndCollegePanel(String courseName, CollegePanel collegePanel);

    // Optional: Delete all courses for a specific college
    void deleteByCollegePanel(CollegePanel collegePanel);
}

