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

import com.triptrove.controller.BookingController;
import com.triptrove.dto.BookingDTO;
import com.triptrove.service.BookingService;
import com.triptrove.utils.MasterData;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class BookingExceptionTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService bookingService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testGetBookingByIdNotFoundException() throws Exception {
		Long bookingId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Booking not found");

		when(this.bookingService.getBookingById(bookingId)).thenThrow(new NotFoundException("Booking not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings/" + bookingId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testBookTourInvalidDataException() throws Exception {
		BookingDTO bookingDTO = new BookingDTO(); // Create an invalid BookingDTO

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/bookings")
				.content(MasterData.asJsonString(bookingDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testCancelBookingNotFoundException() throws Exception {
		Long bookingId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "Booking not found");

		when(this.bookingService.cancelBooking(bookingId)).thenThrow(new NotFoundException("Booking not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/bookings/" + bookingId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}

	@Test
	public void testGetUserBookingsNotFoundException() throws Exception {
		Long userId = 1L;
		ErrorResponse exResponse = new ErrorResponse(HttpStatus.NOT_FOUND.value(), "User not found");

		when(this.bookingService.getUserBookings(userId)).thenThrow(new NotFoundException("User not found"));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings").param("userId", userId.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		yakshaAssert(currentTest(),
				(result.getResponse().getContentAsString().contains(exResponse.getMessage()) ? "true" : "false"),
				exceptionTestFile);
	}
}
