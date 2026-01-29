package com.ramanasoft.college.repository;

import com.ramanasoft.college.model.Gallery;
import com.ramanasoft.college.model.CollegePanel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {

    // Find all gallery items for a specific college
    List<Gallery> findByCollegePanel(CollegePanel collegePanel);

    // Optional: Find all media of a specific type (IMAGE or VIDEO) for a college
    List<Gallery> findByCollegePanelAndMediaType(CollegePanel collegePanel, String mediaType);

    // Optional: Delete all gallery items of a specific college
    void deleteByCollegePanel(CollegePanel collegePanel);
}

