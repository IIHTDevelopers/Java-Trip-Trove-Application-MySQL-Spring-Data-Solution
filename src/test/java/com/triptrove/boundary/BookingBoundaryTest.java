package com.triptrove.boundary;

import static com.triptrove.utils.TestUtils.boundaryTestFile;
import static com.triptrove.utils.TestUtils.currentTest;
import static com.triptrove.utils.TestUtils.testReport;
import static com.triptrove.utils.TestUtils.yakshaAssert;

import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.triptrove.dto.BookingDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class BookingBoundaryTest {

	private static Validator validator;

	@BeforeAll
	public static void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testBookingUserIdNotNull() throws IOException {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setUserId(null);
		Set<ConstraintViolation<BookingDTO>> violations = validator.validate(bookingDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testBookingTourIdNotNull() throws IOException {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setTourId(null);
		Set<ConstraintViolation<BookingDTO>> violations = validator.validate(bookingDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testBookingDateNotNull() throws IOException {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingDate(null);
		Set<ConstraintViolation<BookingDTO>> violations = validator.validate(bookingDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTourDateNotNull() throws IOException {
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setTourDate(null);
		Set<ConstraintViolation<BookingDTO>> violations = validator.validate(bookingDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
