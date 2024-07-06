package com.triptrove.functional;

import static com.triptrove.utils.MasterData.getBookingDTO;
import static com.triptrove.utils.MasterData.getBookingDTOList;
import static com.triptrove.utils.TestUtils.businessTestFile;
import static com.triptrove.utils.TestUtils.currentTest;
import static com.triptrove.utils.TestUtils.testReport;
import static com.triptrove.utils.TestUtils.yakshaAssert;
import static org.mockito.ArgumentMatchers.any;
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

import com.triptrove.controller.BookingController;
import com.triptrove.dto.BookingDTO;
import com.triptrove.service.BookingService;
import com.triptrove.utils.MasterData;

@WebMvcTest(BookingController.class)
@AutoConfigureMockMvc
public class BookingControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private BookingService bookingService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testBookTour() throws Exception {
		BookingDTO bookingDTO = getBookingDTO();

		when(bookingService.bookTour(any())).thenReturn(bookingDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/bookings")
				.content(MasterData.asJsonString(bookingDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookingDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetUserBookings() throws Exception {
		List<BookingDTO> bookingDTOList = getBookingDTOList();
		Long userId = 1L;

		when(bookingService.getUserBookings(userId)).thenReturn(bookingDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings").param("userId", userId.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookingDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetBookingById() throws Exception {
		BookingDTO bookingDTO = getBookingDTO();
		Long bookingId = bookingDTO.getId();

		when(bookingService.getBookingById(bookingId)).thenReturn(bookingDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings/" + bookingId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookingDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testCancelBooking() throws Exception {
		Long bookingId = 1L;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/bookings/" + bookingId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testGetUpcomingBookings() throws Exception {
		List<BookingDTO> bookingDTOList = getBookingDTOList();
		Long userId = 1L;

		when(bookingService.getUpcomingBookings(userId)).thenReturn(bookingDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings/upcoming")
				.param("userId", userId.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookingDTOList))
						? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetPastBookings() throws Exception {
		List<BookingDTO> bookingDTOList = getBookingDTOList();
		Long userId = 1L;

		when(bookingService.getPastBookings(userId)).thenReturn(bookingDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/bookings/past")
				.param("userId", userId.toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(bookingDTOList))
						? "true"
						: "false",
				businessTestFile);
	}
}
