package com.ramanasoft.admin.service;



import com.ramanasoft.admin.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    ReviewDto createReview(ReviewDto dto);
    
    List<ReviewDto> getAllReviews();

    ReviewDto getReviewById(Long id);

    List<ReviewDto> getReviewsByCollege(Long collegeId);
    
    ReviewDto updateReview(ReviewDto dto);

    void deleteReview(Long id);
    
    List<ReviewDto> searchReviews(
            String userName,
            Long collegeId,
            Double rating,
            String comment
    );

}
