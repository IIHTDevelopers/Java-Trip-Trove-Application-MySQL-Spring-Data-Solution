package com.triptrove.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.triptrove.entity.Tour;

public interface TourRepository extends JpaRepository<Tour, Long> {
	// Custom query method
	@Query("SELECT t FROM Tour t WHERE t.title LIKE %:query% OR t.description LIKE %:query%")
	List<Tour> searchTours(@Param("query") String query);

	// Dynamic query method
	List<Tour> findByLocationAndPriceBetweenAndRatingGreaterThanEqual(String location, Double priceLow,
			Double priceHigh, Double rating);
}
