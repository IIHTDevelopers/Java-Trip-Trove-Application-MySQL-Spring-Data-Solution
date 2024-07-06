package com.triptrove.functional;

import static com.triptrove.utils.MasterData.getUserDTO;
import static com.triptrove.utils.TestUtils.businessTestFile;
import static com.triptrove.utils.TestUtils.currentTest;
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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.triptrove.controller.UserController;
import com.triptrove.dto.UserDTO;
import com.triptrove.service.UserService;
import com.triptrove.utils.MasterData;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@AfterAll
	public static void afterAll() {
		testReport();
	}

	@Test
	public void testRegisterUser() throws Exception {
		UserDTO userDTO = getUserDTO();

		when(userService.registerUser(any())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/users/register")
				.content(MasterData.asJsonString(userDTO)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testGetUserProfile() throws Exception {
		UserDTO userDTO = getUserDTO();

		when(userService.getUserProfile(userDTO.getId())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/users/profile")
				.param("userId", userDTO.getId().toString()).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}

	@Test
	public void testUpdateUserProfile() throws Exception {
		UserDTO userDTO = getUserDTO();

		when(userService.updateUserProfile(eq(userDTO.getId()), any())).thenReturn(userDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/users/profile")
				.param("userId", userDTO.getId().toString()).content(MasterData.asJsonString(userDTO))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		yakshaAssert(currentTest(),
				result.getResponse().getContentAsString().contentEquals(MasterData.asJsonString(userDTO)) ? "true"
						: "false",
				businessTestFile);
	}
}
