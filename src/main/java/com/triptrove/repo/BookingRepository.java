package com.triptrove.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.triptrove.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Long> {
	// Custom query method
	@Query("SELECT b FROM Booking b WHERE b.user.id = :userId AND b.tourDate >= :today")
	List<Booking> findUpcomingBookingsByUserId(@Param("userId") Long userId, @Param("today") LocalDate today);

	// Dynamic query method
	List<Booking> findByUserIdAndTourDateBefore(Long userId, LocalDate tourDate);

	// Find bookings by user ID
	List<Booking> findByUserId(Long userId);
}
