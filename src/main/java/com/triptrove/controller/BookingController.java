package com.triptrove.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.triptrove.dto.BookingDTO;
import com.triptrove.service.BookingService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<BookingDTO> bookTour(@RequestBody @Valid BookingDTO bookingDTO) {
		BookingDTO createdBooking = bookingService.bookTour(bookingDTO);
		return ResponseEntity.ok(createdBooking);
	}

	@GetMapping
	public ResponseEntity<List<BookingDTO>> getUserBookings(@RequestParam Long userId) {
		List<BookingDTO> bookings = bookingService.getUserBookings(userId);
		return ResponseEntity.ok(bookings);
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable Long id) {
		BookingDTO booking = bookingService.getBookingById(id);
		return ResponseEntity.ok(booking);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> cancelBooking(@PathVariable Long id) {
		bookingService.cancelBooking(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/upcoming")
	public ResponseEntity<List<BookingDTO>> getUpcomingBookings(@RequestParam Long userId) {
		List<BookingDTO> bookings = bookingService.getUpcomingBookings(userId);
		return ResponseEntity.ok(bookings);
	}

	@GetMapping("/past")
	public ResponseEntity<List<BookingDTO>> getPastBookings(@RequestParam Long userId) {
		List<BookingDTO> bookings = bookingService.getPastBookings(userId);
		return ResponseEntity.ok(bookings);
	}
}
