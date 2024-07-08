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

import com.triptrove.dto.TourDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class TourBoundaryTest {

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
	public void testTitleNotNull() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setTitle(null);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTitleNotBlank() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setTitle("");
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDescriptionNotNull() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setDescription(null);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testDescriptionNotBlank() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setDescription("");
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testLocationNotNull() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setLocation(null);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testLocationNotBlank() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setLocation("");
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPriceNotNull() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setPrice(null);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testPricePositive() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setPrice(-1.0);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTourRatingNotNull() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setRating(null);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTourRatingPositive() throws IOException {
		TourDTO tourDTO = new TourDTO();
		tourDTO.setRating(-1.0);
		Set<ConstraintViolation<TourDTO>> violations = validator.validate(tourDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
