package com.triptrove.exception;

import static com.triptrove.utils.TestUtils.currentTest;
import static com.triptrove.utils.TestUtils.exceptionTestFile;
import static com.triptrove.utils.TestUtils.testReport;
import static com.triptrove.utils.TestUtils.yakshaAssert;
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

import com.triptrove.controller.TourController;
import com.triptrove.dto.TourDTO;
import com.triptrove.service.TourService;
import com.triptrove.utils.MasterData;

@WebMvcTest(TourController.class)
@AutoConfigureMockMvc
public class TourExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TourService tourService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetTourByIdNotFoundException() throws Exception {
		Long tourId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Tour not found");

		when(this.tourService.getTourById(tourId)).thenThrow(new NotFoundException("Tour not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tours/" + tourId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testCreateTourInvalidDataException() throws Exception {
		TourDTO tourDTO = new TourDTO(); // Create an invalid TourDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/tours")
				.content(MasterData.asJsonString(tourDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testUpdateTourInvalidDataException() throws Exception {
		TourDTO tourDTO = new TourDTO(); // Create an invalid TourDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/tours/1")
				.content(MasterData.asJsonString(tourDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testDeleteTourByIdNotFoundException() throws Exception {
		Long tourId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Tour not found");

		when(this.tourService.deleteTour(tourId)).thenThrow(new NotFoundException("Tour not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/tours/" + tourId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
