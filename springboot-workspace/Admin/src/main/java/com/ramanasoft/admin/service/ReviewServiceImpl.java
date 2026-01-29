package com.ramanasoft.admin.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ramanasoft.admin.dto.ReviewDto;
import com.ramanasoft.admin.model.College;
import com.ramanasoft.admin.model.Review;
import com.ramanasoft.admin.repository.CollegeRepository;
import com.ramanasoft.admin.repository.ReviewRepository;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private CollegeRepository collegeRepository;

    // ======================
    // CREATE
    // ======================
    @Override
    public ReviewDto createReview(ReviewDto dto) {

        if (dto.getCollegeId() == null) {
            throw new IllegalArgumentException("collegeId must not be null");
        }

        College college = collegeRepository.findById(dto.getCollegeId())
                .orElseThrow(() -> new RuntimeException("College not found"));

        Review review = new Review();
        review.setCollege(college);
        review.setUserName(dto.getUserName());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Review saved = reviewRepository.save(review);
        return mapToDto(saved);
    }

    // ======================
    // GET ALL
    // ======================
    @Override
    public List<ReviewDto> getAllReviews() {
        return reviewRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // GET BY ID
    // ======================
    @Override
    public ReviewDto getReviewById(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id " + id));
        return mapToDto(review);
    }

    // ======================
    // GET BY COLLEGE
    // ======================
    @Override
    public List<ReviewDto> getReviewsByCollege(Long collegeId) {
        return reviewRepository.findByCollegeId(collegeId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    // ======================
    // UPDATE
    // ======================
    @Override
    public ReviewDto updateReview(ReviewDto dto) {

        if (dto.getId() == null) {
            throw new IllegalArgumentException("Review ID must not be null");
        }

        Review review = reviewRepository.findById(dto.getId())
                .orElseThrow(() -> new RuntimeException("Review not found with id " + dto.getId()));

        review.setUserName(dto.getUserName());
        review.setRating(dto.getRating());
        review.setComment(dto.getComment());

        Review updated = reviewRepository.save(review);
        return mapToDto(updated);
    }

    // ======================
    // DELETE
    // ======================
    @Override
    public void deleteReview(Long id) {
        if (!reviewRepository.existsById(id)) {
            throw new RuntimeException("Review not found with id " + id);
        }
        reviewRepository.deleteById(id);
    }

    // ======================
    // MAPPING METHOD
    // ======================
    private ReviewDto mapToDto(Review review) {
        return new ReviewDto(
                review.getId(),
                review.getCollege().getId(),
                review.getUserName(),
                review.getRating(),
                review.getComment()
        );
    }
}
