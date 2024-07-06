package com.triptrove.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.triptrove.dto.BookingDTO;
import com.triptrove.dto.ReviewDTO;
import com.triptrove.dto.TourDTO;
import com.triptrove.dto.UserDTO;

public class MasterData {

	public static UserDTO getUserDTO() {
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setUsername("john_doe");
		userDTO.setEmail("john.doe@example.com");
		userDTO.setPassword("password123");
		userDTO.setFullName("John Doe");
		return userDTO;
	}

	public static List<UserDTO> getUserDTOList() {
		List<UserDTO> userDTOList = new ArrayList<>();
		userDTOList.add(getUserDTO());
		return userDTOList;
	}

	public static TourDTO getTourDTO() {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setId(1L);
		tourDTO.setTitle("Amazing Safari");
		tourDTO.setDescription("Experience the wild like never before.");
		tourDTO.setLocation("Africa");
		tourDTO.setPrice(1500.00);
		tourDTO.setRating(4.5);
		return tourDTO;
	}

	public static List<TourDTO> getTourDTOList() {
		List<TourDTO> tourDTOList = new ArrayList<>();
		tourDTOList.add(getTourDTO());
		return tourDTOList;
	}

	public static BookingDTO getBookingDTO() {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setId(1L);
		bookingDTO.setUserId(getUserDTO().getId());
		bookingDTO.setTourId(getTourDTO().getId());
		bookingDTO.setBookingDate(LocalDate.of(2023, 7, 1));
		bookingDTO.setTourDate(LocalDate.of(2023, 8, 1));
		return bookingDTO;
	}

	public static List<BookingDTO> getBookingDTOList() {
		List<BookingDTO> bookingDTOList = new ArrayList<>();
		bookingDTOList.add(getBookingDTO());
		return bookingDTOList;
	}

	public static ReviewDTO getReviewDTO() {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setId(1L);
		reviewDTO.setTourId(getTourDTO().getId());
		reviewDTO.setUserId(getUserDTO().getId());
		reviewDTO.setComment("Amazing experience!");
		reviewDTO.setRating(5.0);
		return reviewDTO;
	}

	public static List<ReviewDTO> getReviewDTOList() {
		List<ReviewDTO> reviewDTOList = new ArrayList<>();
		reviewDTOList.add(getReviewDTO());
		return reviewDTOList;
	}

	public static String asJsonString(final Object obj) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			final String jsonContent = mapper.writeValueAsString(obj);
			return jsonContent;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String randomStringWithSize(int size) {
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < size; i++) {
			s.append("A");
		}
		return s.toString();
	}
}
