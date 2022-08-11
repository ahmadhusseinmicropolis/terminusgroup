package com.terminusgroup.assessment;

import com.terminusgroup.assessment.Repositories.UserRepository;
import com.terminusgroup.assessment.controllers.UserController;
import com.terminusgroup.assessment.modelsDtos.UserDto;
import com.terminusgroup.assessment.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserControllerTest {

	private MockMvc mockMvc;
	
	ObjectMapper objectMapper = new ObjectMapper();
	ObjectWriter objectWriter = objectMapper.writer();

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	UserDto user_1 = new UserDto(Long.valueOf(1), "Ahmad", "Hussein", "Software Engineer", 30);
	UserDto user_2 = new UserDto(Long.valueOf(2), "Shinyi", "NG", "HR Manager", 25);
	UserDto user_3 = new UserDto(Long.valueOf(1), "Adam", "kane", "Ui/Ux", 30);

	@Before
	public void setUp(){
		MockitoAnnotations.openMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void getUsersTest() throws Exception{
		List<UserDto> users = new ArrayList<>(Arrays.asList(user_1, user_2, user_3));
		Mockito.when(userService.getAll()).thenReturn(users);

		mockMvc.perform(MockMvcRequestBuilders
					.get("/api/users")
					.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void getUserByIdTest() throws Exception{
		Mockito.when(userService.getById(user_1.getId())).thenReturn(user_1);
		mockMvc.perform(MockMvcRequestBuilders
						.get("/api/users/1")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", notNullValue()));
	}

	@Test
	public void createUserTest() throws Exception{
		UserDto newUser = UserDto.builder()
				.id(Long.valueOf(4))
				.firstName("Maram")
				.lastName("Bakar")
				.occupation("Mobile Developer")
				.age(24)
				.build();

		Mockito.when(userService.create(newUser)).thenReturn(newUser);

		String content = objectWriter.writeValueAsString(newUser);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());

	}

	@Test
	public void updateUserTest() throws Exception{
		UserDto updatedUser = UserDto.builder()
				.id(Long.valueOf(1))
				.firstName("Ahmad")
				.lastName("Hussein")
				.occupation("ML Engineer")
				.age(31)
				.build();

		Mockito.when(userService.getById(user_1.getId())).thenReturn(user_1).thenReturn(null);

		Mockito.when(userService.create(updatedUser)).thenReturn(updatedUser);

		String content = objectWriter.writeValueAsString(updatedUser);

		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(content)
				.accept(MediaType.APPLICATION_JSON);

		mockMvc.perform(mockRequest)
				.andExpect(status().isOk());
	}

	@Test
	public void deleteUserTest() throws Exception{
		Mockito.when(userService.getById(user_2.getId())).thenReturn(user_2).thenReturn(null);

		mockMvc.perform(MockMvcRequestBuilders
						.delete("/api/users/2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}

}
