package com.triptrove.functional;

import static com.triptrove.utils.MasterData.getTourDTO;
import static com.triptrove.utils.MasterData.getTourDTOList;
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

import com.triptrove.controller.TourController;
import com.triptrove.dto.TourDTO;
import com.triptrove.service.TourService;
import com.triptrove.utils.MasterData;

@WebMvcTest(TourController.class)
@AutoConfigureMockMvc
public class TourControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private TourService tourService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testCreateTour() throws Exception {
		TourDTO tourDTO = getTourDTO();

		when(tourService.createTour(any())).thenReturn(tourDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/tours")
				.content(MasterData.asJsonString(tourDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetAllTours() throws Exception {
		List<TourDTO> tourDTOList = getTourDTOList();

		when(tourService.getAllTours()).thenReturn(tourDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tours").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTOList)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetTourById() throws Exception {
		TourDTO tourDTO = getTourDTO();

		when(tourService.getTourById(tourDTO.getId())).thenReturn(tourDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tours/" + tourDTO.getId())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdateTour() throws Exception {
		TourDTO tourDTO = getTourDTO();

		when(tourService.updateTour(eq(tourDTO.getId()), any())).thenReturn(tourDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/tours/" + tourDTO.getId())
				.content(MasterData.asJsonString(tourDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testDeleteTour() throws Exception {
		Long tourId = 1L;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/tours/" + tourId)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(), (result.getResponse().getContentAsString().contentEquals("") ? "true" : "false"),
				businessTestFile);
	}

	@Test
	public void testSearchTours() throws Exception {
		List<TourDTO> tourDTOList = getTourDTOList();
		String query = "Safari";

		when(tourService.searchTours(eq(query))).thenReturn(tourDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tours/search").param("query", query)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTOList)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testFilterTours() throws Exception {
		List<TourDTO> tourDTOList = getTourDTOList();
		String location = "Africa";
		String priceRange = "1000-2000";
		Double rating = 4.0;

		when(tourService.filterTours(eq(location), eq(priceRange), eq(rating))).thenReturn(tourDTOList);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tours/filter").param("location", location)
				.param("priceRange", priceRange).param("rating", rating.toString())
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(tourDTOList)) ? "true"
						: "false",
				businessTestFile);
	}
}
