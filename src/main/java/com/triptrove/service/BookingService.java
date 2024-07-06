package com.triptrove.service;

import java.util.List;

import com.triptrove.dto.BookingDTO;

public interface BookingService {
	BookingDTO bookTour(BookingDTO bookingDTO);

	List<BookingDTO> getUserBookings(Long userId);

	BookingDTO getBookingById(Long bookingId);

	Boolean cancelBooking(Long bookingId);

	List<BookingDTO> getUpcomingBookings(Long userId);

	List<BookingDTO> getPastBookings(Long userId);
}
