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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triptrove.dto.TourDTO;
import com.triptrove.service.TourService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tours")
public class TourController {

	@Autowired
	private TourService tourService;

	@PostMapping
	public ResponseEntity<TourDTO> createTour(@RequestBody @Valid TourDTO tourDTO) {
		TourDTO createdTour = tourService.createTour(tourDTO);
		return ResponseEntity.ok(createdTour);
	}

	@GetMapping
	public ResponseEntity<List<TourDTO>> getAllTours() {
		List<TourDTO> tours = tourService.getAllTours();
		return ResponseEntity.ok(tours);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TourDTO> getTourById(@PathVariable Long id) {
		TourDTO tour = tourService.getTourById(id);
		return ResponseEntity.ok(tour);
	}

	@PutMapping("/{id}")
	public ResponseEntity<TourDTO> updateTour(@PathVariable Long id, @RequestBody @Valid TourDTO tourDTO) {
		TourDTO updatedTour = tourService.updateTour(id, tourDTO);
		return ResponseEntity.ok(updatedTour);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
		tourService.deleteTour(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/search")
	public ResponseEntity<List<TourDTO>> searchTours(@RequestParam String query) {
		List<TourDTO> tours = tourService.searchTours(query);
		return ResponseEntity.ok(tours);
	}

	@GetMapping("/filter")
	public ResponseEntity<List<TourDTO>> filterTours(@RequestParam String location, @RequestParam String priceRange,
			@RequestParam Double rating) {
		List<TourDTO> tours = tourService.filterTours(location, priceRange, rating);
		return ResponseEntity.ok(tours);
	}
}
