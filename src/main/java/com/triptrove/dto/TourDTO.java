package com.triptrove.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class TourDTO {
	private Long id;

	@NotBlank(message = "Title is mandatory")
	private String title;

	@NotBlank(message = "Description is mandatory")
	private String description;

	@NotBlank(message = "Location is mandatory")
	private String location;

	@NotNull(message = "Price is mandatory")
	@Positive(message = "Price must be positive")
	private Double price;

	@NotNull(message = "Rating is mandatory")
	@Positive(message = "Rating must be positive")
	private Double rating;

	public TourDTO() {
		super();
	}

	public TourDTO(Long id, @NotBlank(message = "Title is mandatory") String title,
			@NotBlank(message = "Description is mandatory") String description,
			@NotBlank(message = "Location is mandatory") String location,
			@NotNull(message = "Price is mandatory") @Positive(message = "Price must be positive") Double price,
			@NotNull(message = "Rating is mandatory") @Positive(message = "Rating must be positive") Double rating) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.location = location;
		this.price = price;
		this.rating = rating;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}
}
