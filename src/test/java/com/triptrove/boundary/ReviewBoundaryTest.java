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

import com.triptrove.dto.ReviewDTO;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

public class ReviewBoundaryTest {

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
	public void testUserIdNotNull() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setUserId(null);
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testTourIdNotNull() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setTourId(null);
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCommentNotNull() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setComment(null);
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCommentNotBlank() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setComment("");
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testCommentSize() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setComment("a".repeat(501));
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testRatingNotNull() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setRating(null);
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}

	@Test
	public void testRatingPositive() throws IOException {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setRating(-1.0);
		Set<ConstraintViolation<ReviewDTO>> violations = validator.validate(reviewDTO);
		try {
			yakshaAssert(currentTest(), !violations.isEmpty(), boundaryTestFile);
		} catch (Exception e) {
			yakshaAssert(currentTest(), false, boundaryTestFile);
		}
	}
}
