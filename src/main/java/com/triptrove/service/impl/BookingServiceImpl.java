package com.triptrove.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.triptrove.dto.BookingDTO;
import com.triptrove.entity.Booking;
import com.triptrove.entity.Tour;
import com.triptrove.entity.User;
import com.triptrove.exception.NotFoundException;
import com.triptrove.repo.BookingRepository;
import com.triptrove.repo.TourRepository;
import com.triptrove.repo.UserRepository;
import com.triptrove.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private TourRepository tourRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public BookingDTO bookTour(BookingDTO bookingDTO) {
		Tour tour = tourRepository.findById(bookingDTO.getTourId())
				.orElseThrow(() -> new NotFoundException("Tour not found"));
		User user = userRepository.findById(bookingDTO.getUserId())
				.orElseThrow(() -> new NotFoundException("User not found"));

		Booking booking = new Booking();
		booking.setTour(tour);
		booking.setUser(user);
		booking.setBookingDate(bookingDTO.getBookingDate());
		booking.setTourDate(bookingDTO.getTourDate());

		booking = bookingRepository.save(booking);

		return convertToDTO(booking);
	}

	@Override
	public List<BookingDTO> getUserBookings(Long userId) {
		List<Booking> bookings = bookingRepository.findByUserId(userId);
		return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public BookingDTO getBookingById(Long bookingId) {
		Booking booking = bookingRepository.findById(bookingId)
				.orElseThrow(() -> new NotFoundException("Booking not found"));
		return convertToDTO(booking);
	}

	@Override
	public Boolean cancelBooking(Long bookingId) {
		if (!bookingRepository.existsById(bookingId)) {
			throw new NotFoundException("Booking not found");
		}
		bookingRepository.deleteById(bookingId);
		return true;
	}

	@Override
	public List<BookingDTO> getUpcomingBookings(Long userId) {
		List<Booking> bookings = bookingRepository.findUpcomingBookingsByUserId(userId, LocalDate.now());
		return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	@Override
	public List<BookingDTO> getPastBookings(Long userId) {
		List<Booking> bookings = bookingRepository.findByUserIdAndTourDateBefore(userId, LocalDate.now());
		return bookings.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	private BookingDTO convertToDTO(Booking booking) {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setId(booking.getId());
		bookingDTO.setUserId(booking.getUser().getId());
		bookingDTO.setTourId(booking.getTour().getId());
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setTourDate(booking.getTourDate());
		return bookingDTO;
	}
}
