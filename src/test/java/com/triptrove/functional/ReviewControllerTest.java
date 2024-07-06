package com.triptrove.functional;

import static com.triptrove.utils.MasterData.getReviewDTO;
import static com.triptrove.utils.MasterData.getReviewDTOList;
import static com.triptrove.utils.TestUtils.businessTestFile;
import static com.triptrove.utils.TestUtils.currentTest;
import static com.triptrove.utils.TestUtils.testReport;
import static com.triptrove.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class ReviewControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ReviewService reviewService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testAddReview() throws Exception {
		ReviewDTO reviewDTO = getReviewDTO();

		when(reviewService.addReview(any())).thenReturn(reviewDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/reviews")
				.content(MasterData.asJsonString(reviewDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(reviewDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetReviewsByTourId() throws Exception {
		List<ReviewDTO> reviewDTOList = getReviewDTOList();
		Long tourId = 1L;

		when(reviewService.getReviewsByTourId(tourId)).thenReturn(reviewDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reviews/tour/" + tourId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(reviewDTOList)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdateReview() throws Exception {
		ReviewDTO reviewDTO = getReviewDTO();

		when(reviewService.updateReview(eq(reviewDTO.getId()), any())).thenReturn(reviewDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/reviews/" + reviewDTO.getId())
				.content(MasterData.asJsonString(reviewDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(reviewDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testDeleteReview() throws Exception {
		Long reviewId = 1L;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/reviews/" + reviewId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetReviewsByUserId() throws Exception {
		List<ReviewDTO> reviewDTOList = getReviewDTOList();
		Long userId = 1L;

		when(reviewService.getReviewsByUserId(userId)).thenReturn(reviewDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/reviews/user/" + userId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(reviewDTOList)) ? "true"
						: "false",
				businessTestFile);
	}
}
