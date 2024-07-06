package com.triptrove.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.triptrove.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
	// Custom query method
	@Query("SELECT r FROM Review r WHERE r.tour.id = :tourId")
	List<Review> findByTourId(@Param("tourId") Long tourId);

	// Dynamic query method
	List<Review> findByUserId(Long userId);
}
