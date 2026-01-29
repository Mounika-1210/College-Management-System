package com.ramanasoft.admin.repository;

import com.ramanasoft.admin.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCollegeId(Long collegeId);
}