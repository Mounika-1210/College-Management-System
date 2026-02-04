package com.ramanasoft.admin.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ramanasoft.admin.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findByCollegeId(Long collegeId);
    
    @Query("""
            SELECT r FROM Review r WHERE
            (:userName IS NULL OR LOWER(r.userName) LIKE LOWER(CONCAT('%', :userName, '%'))) AND
            (:collegeId IS NULL OR r.college.id = :collegeId) AND
            (:rating IS NULL OR r.rating = :rating) AND
            (:comment IS NULL OR LOWER(r.comment) LIKE LOWER(CONCAT('%', :comment, '%')))
        """)
        List<Review> searchReviews(
                @Param("userName") String userName,
                @Param("collegeId") Long collegeId,
                @Param("rating") Double rating,
                @Param("comment") String comment
        );
}