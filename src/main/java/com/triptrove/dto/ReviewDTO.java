package com.triptrove.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class ReviewDTO {
	private Long id;

	@NotNull(message = "User ID is mandatory")
	private Long userId;

	@NotNull(message = "Tour ID is mandatory")
	private Long tourId;

	@NotBlank(message = "Comment is mandatory")
	@Size(max = 500, message = "Comment must be less than 500 characters")
	private String comment;

	@NotNull(message = "Rating is mandatory")
	@Positive(message = "Rating must be positive")
	private Double rating;

	public ReviewDTO() {
		super();
	}

	public ReviewDTO(Long id, @NotNull(message = "User ID is mandatory") Long userId,
			@NotNull(message = "Tour ID is mandatory") Long tourId,
			@NotBlank(message = "Comment is mandatory") @Size(max = 500, message = "Comment must be less than 500 characters") String comment,
			@NotNull(message = "Rating is mandatory") @Positive(message = "Rating must be positive") Double rating) {
		super();
		this.id = id;
		this.userId = userId;
		this.tourId = tourId;
		this.comment = comment;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getTourId() {
		return tourId;
	}

	public void setTourId(Long tourId) {
		this.tourId = tourId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
