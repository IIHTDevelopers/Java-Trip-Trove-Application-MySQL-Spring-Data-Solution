package com.triptrove.service;

import java.util.List;

import com.triptrove.dto.TourDTO;

public interface TourService {
	TourDTO createTour(TourDTO tourDTO);

	List<TourDTO> getAllTours();

	TourDTO getTourById(Long tourId);

	TourDTO updateTour(Long tourId, TourDTO tourDTO);

	Boolean deleteTour(Long tourId);

	List<TourDTO> searchTours(String query);

	List<TourDTO> filterTours(String location, String priceRange, Double rating);
}
