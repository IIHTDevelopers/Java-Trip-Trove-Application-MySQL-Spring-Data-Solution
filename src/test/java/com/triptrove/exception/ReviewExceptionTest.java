package com.triptrove.exception;

import static com.triptrove.utils.TestUtils.currentTest;
import static com.triptrove.utils.TestUtils.exceptionTestFile;
import static com.triptrove.utils.TestUtils.testReport;
import static com.triptrove.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.triptrove.controller.ReviewController;
import com.triptrove.dto.ReviewDTO;
import com.triptrove.service.ReviewService;
import com.triptrove.utils.MasterData;

@WebMvcTest(ReviewController.class)
@AutoConfigureMockMvc
public class ReviewExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetReviewsByTourIdNotFoundException() throws Exception {
		Long tourId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Tour not found");

		when(this.reviewService.getReviewsByTourId(tourId)).thenThrow(new NotFoundException("Tour not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reviews/tour/" + tourId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testAddReviewInvalidDataException() throws Exception {
		ReviewDTO reviewDTO = new ReviewDTO(); // Create an invalid ReviewDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/reviews")
				.content(MasterData.asJsonString(reviewDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateReviewNotFoundException() throws Exception {
		Long reviewId = 1L;
		ReviewDTO reviewDTO = MasterData.getReviewDTO(); // Create a valid ReviewDTO
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Review not found");

		when(this.reviewService.updateReview(eq(reviewId), any())).thenThrow(new NotFoundException("Review not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/reviews/" + reviewId)
				.content(MasterData.asJsonString(reviewDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteReviewByIdNotFoundException() throws Exception {
		Long reviewId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Review not found");

		when(this.reviewService.deleteReview(reviewId)).thenThrow(new NotFoundException("Review not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/reviews/" + reviewId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetReviewsByUserIdNotFoundException() throws Exception {
		Long userId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found");

		when(this.reviewService.getReviewsByUserId(userId)).thenThrow(new NotFoundException("User not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reviews/user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
