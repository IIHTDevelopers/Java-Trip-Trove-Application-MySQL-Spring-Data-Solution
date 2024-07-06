package com.triptrove.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptrove.dto.TourDTO;
import com.triptrove.entity.Tour;
import com.triptrove.exception.NotFoundException;
import com.triptrove.repo.TourRepository;
import com.triptrove.service.TourService;

@Service
public class TourServiceImpl implements TourService {

	@Autowired
	private TourRepository tourRepository;

	@Override
	public TourDTO createTour(TourDTO tourDTO) {
		Tour tour = new Tour();
		tour.setTitle(tourDTO.getTitle());
		tour.setDescription(tourDTO.getDescription());
		tour.setLocation(tourDTO.getLocation());
		tour.setPrice(tourDTO.getPrice());
		tour.setRating(tourDTO.getRating());

		tour = tourRepository.save(tour);

		return convertToDTO(tour);
	}

	@Override
	public List<TourDTO> getAllTours() {
		List<Tour> tours = tourRepository.findAll();
		return tours.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public TourDTO getTourById(Long tourId) {
		Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new NotFoundException("Tour not found"));
		return convertToDTO(tour);
	}

	@Override
	public TourDTO updateTour(Long tourId, TourDTO tourDTO) {
		Tour tour = tourRepository.findById(tourId).orElseThrow(() -> new NotFoundException("Tour not found"));

		tour.setTitle(tourDTO.getTitle());
		tour.setDescription(tourDTO.getDescription());
		tour.setLocation(tourDTO.getLocation());
		tour.setPrice(tourDTO.getPrice());
		tour.setRating(tourDTO.getRating());

		tour = tourRepository.save(tour);

		return convertToDTO(tour);
	}

	@Override
	public Boolean deleteTour(Long tourId) {
		if (!tourRepository.existsById(tourId)) {
			throw new NotFoundException("Tour not found");
		}
		tourRepository.deleteById(tourId);
		return true;
	}

	@Override
	public List<TourDTO> searchTours(String query) {
		List<Tour> tours = tourRepository.searchTours(query);
		return tours.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<TourDTO> filterTours(String location, String priceRange, Double rating) {
		String[] priceRangeArray = priceRange.split("-");
		Double priceLow = Double.valueOf(priceRangeArray[0]);
		Double priceHigh = Double.valueOf(priceRangeArray[1]);
		List<Tour> tours = tourRepository.findByLocationAndPriceBetweenAndRatingGreaterThanEqual(location, priceLow,
				priceHigh, rating);
		return tours.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private TourDTO convertToDTO(Tour tour) {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setId(tour.getId());
		tourDTO.setTitle(tour.getTitle());
		tourDTO.setDescription(tour.getDescription());
		tourDTO.setLocation(tour.getLocation());
		tourDTO.setPrice(tour.getPrice());
		tourDTO.setRating(tour.getRating());
		return tourDTO;
	}
}
