package com.ramanasoft.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ramanasoft.admin.dto.ReviewDto;
import com.ramanasoft.admin.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/admin/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // ======================
    // CREATE REVIEW
    // ======================
    @PostMapping
    public ReviewDto createReview(
            @Valid @RequestBody ReviewDto dto) {
        return reviewService.createReview(dto);
    }

    // ======================
    // GET ALL REVIEWS
    // ======================
    @GetMapping
    public List<ReviewDto> getAllReviews() {
        return reviewService.getAllReviews();
    }

    // ======================
    // GET REVIEW BY ID
    // ======================
    @GetMapping("/{id}")
    public ReviewDto getReviewById(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    // ======================
    // GET REVIEWS BY COLLEGE
    // ======================
    @GetMapping("/college/{collegeId}")
    public List<ReviewDto> getReviewsByCollege(@PathVariable Long collegeId) {
        return reviewService.getReviewsByCollege(collegeId);
    }

    // ======================
    // UPDATE REVIEW
    // ======================
    @PutMapping("/{id}")
    public ReviewDto updateReview(
            @PathVariable Long id,
            @Valid @RequestBody ReviewDto dto) {

        dto.setId(id);
        return reviewService.updateReview(dto);
    }

    // ======================
    // DELETE REVIEW
    // ======================
    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
        return "Review deleted successfully";
    }
}
