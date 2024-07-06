package com.triptrove.service;

import java.util.List;

import com.triptrove.dto.ReviewDTO;

public interface ReviewService {
	ReviewDTO addReview(ReviewDTO reviewDTO);

	List<ReviewDTO> getReviewsByTourId(Long tourId);

	ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO);

	Boolean deleteReview(Long reviewId);

	List<ReviewDTO> getReviewsByUserId(Long userId);
}
