package com.triptrove.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptrove.dto.ReviewDTO;
import com.triptrove.entity.Review;
import com.triptrove.entity.Tour;
import com.triptrove.entity.User;
import com.triptrove.exception.NotFoundException;
import com.triptrove.repo.ReviewRepository;
import com.triptrove.repo.TourRepository;
import com.triptrove.repo.UserRepository;
import com.triptrove.service.ReviewService;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;

	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public ReviewDTO addReview(ReviewDTO reviewDTO) {
		Tour tour = tourRepository.findById(reviewDTO.getTourId())
				.orElseThrow(() -> new NotFoundException("Tour not found"));
		User user = userRepository.findById(reviewDTO.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found"));

		Review review = new Review();
		review.setTour(tour);
		review.setUser(user);
		review.setComment(reviewDTO.getComment());
		review.setRating(reviewDTO.getRating());

		review = reviewRepository.save(review);

		return convertToDTO(review);
	}

	@Override
	public List<ReviewDTO> getReviewsByTourId(Long tourId) {
		List<Review> reviews = reviewRepository.findByTourId(tourId);
		return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public ReviewDTO updateReview(Long reviewId, ReviewDTO reviewDTO) {
		Review review = reviewRepository.findById(reviewId)
				.orElseThrow(() -> new NotFoundException("Review not found"));

		review.setComment(reviewDTO.getComment());
		review.setRating(reviewDTO.getRating());

		review = reviewRepository.save(review);

		return convertToDTO(review);
	}

	@Override
	public Boolean deleteReview(Long reviewId) {
		if (!reviewRepository.existsById(reviewId)) {
			throw new NotFoundException("Review not found");
		}
		reviewRepository.deleteById(reviewId);
		return true;
	}

	@Override
	public List<ReviewDTO> getReviewsByUserId(Long userId) {
		List<Review> reviews = reviewRepository.findByUserId(userId);
		return reviews.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private ReviewDTO convertToDTO(Review review) {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setId(review.getId());
		reviewDTO.setTourId(review.getTour().getId());
		reviewDTO.setUserId(review.getUser().getId());
		reviewDTO.setComment(review.getComment());
		reviewDTO.setRating(review.getRating());
		return reviewDTO;
	}
}
