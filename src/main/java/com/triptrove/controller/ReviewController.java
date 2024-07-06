package com.triptrove.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.triptrove.dto.ReviewDTO;
import com.triptrove.service.ReviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

	@Autowired
	private ReviewService reviewService;

	@PostMapping
	public ResponseEntity<ReviewDTO> addReview(@RequestBody @Valid ReviewDTO reviewDTO) {
		ReviewDTO createdReview = reviewService.addReview(reviewDTO);
		return ResponseEntity.ok(createdReview);
	}

	@GetMapping("/tour/{tourId}")
	public ResponseEntity<List<ReviewDTO>> getReviewsByTourId(@PathVariable Long tourId) {
		List<ReviewDTO> reviews = reviewService.getReviewsByTourId(tourId);
		return ResponseEntity.ok(reviews);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ReviewDTO> updateReview(@PathVariable Long id, @RequestBody @Valid ReviewDTO reviewDTO) {
		ReviewDTO updatedReview = reviewService.updateReview(id, reviewDTO);
		return ResponseEntity.ok(updatedReview);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<ReviewDTO>> getReviewsByUserId(@PathVariable Long userId) {
		List<ReviewDTO> reviews = reviewService.getReviewsByUserId(userId);
		return ResponseEntity.ok(reviews);
	}
}
