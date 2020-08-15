package cl.com.ionix.testbackend.web.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.event.annotation.BeforeTestExecution;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import cl.com.ionix.testbackend.TestBackendApplication;
import  cl.com.ionix.testbackend.persistence.model.User;
import cl.com.ionix.testbackend.persistence.service.IUserService;



@SpringBootTest
@ContextConfiguration(classes = TestBackendApplication.class)
class UserControllerTest {

	private String USERS_ENDPOINT = "/v1/users/";
	private String JSON_BODY = "{\"name\":\"60910000-1\",\"username\":\"96439000-2\",\"email\":\"1699rr@gmail.com\",\"phone\":\"8943\"}";
	private String JSON_BODY_BAB_REQUEST = "{\"name\":\"60910000-1\",\"username\":\"96439000-2\",\"email\":\"test\",\"phone\":\"8943\"}";

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext webApplicationContext;



	@MockBean
	private IUserService userService;;



	@MockBean
	RestTemplate restTemplate;

	@BeforeEach
	protected void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	public User createUser() {
		User u = new User();
		u.setEmail("1699rr@gmail.com");
		u.setName("60910000-1");
		u.setPhone("8943");
		u.setUsername("96439000-2");
		return u;
	}

	@Test
	public void createUserServerErrorTest() {
		try { 

			when(userService.save(ArgumentMatchers.any(User.class))).thenThrow(NullPointerException.class);
			mockMvc
			.perform(MockMvcRequestBuilders.post(USERS_ENDPOINT+"add")
					.content(JSON_BODY)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isInternalServerError()).andReturn();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}

	@Test
	public void createUserBadRequestTest() {
		try {mockMvc
			.perform(MockMvcRequestBuilders.post(USERS_ENDPOINT+"add")
					.content(JSON_BODY_BAB_REQUEST)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isBadRequest()).andReturn();

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}
	@Test
	public void createUserTest() {
		try {
			when(userService.save(ArgumentMatchers.any(User.class))).thenReturn(createUser());
			MvcResult mvcResult =  mockMvc
					.perform(MockMvcRequestBuilders.post(USERS_ENDPOINT+"add")
							.content(JSON_BODY)
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isCreated()).andReturn();
			String content = mvcResult.getResponse().getContentAsString();
			assertTrue(content.contains(createUser().getName()));

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}
	@Test
	public void createUserEmailAlreadyExistsTest() {
		try {

			Optional<User> userOptional = Optional.of(createUser());
			when(userService.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(userOptional);
			when(userService.save(ArgumentMatchers.any(User.class))).thenReturn(createUser());
			mockMvc
					.perform(MockMvcRequestBuilders.post(USERS_ENDPOINT+"add")
							.content(JSON_BODY)
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isPreconditionFailed()).andReturn();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}

	@Test
	public void consultaUserTest() {
		try {
			Optional<User> userOptional = Optional.of(createUser());
			when(userService.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(userOptional);
			MvcResult mvcResult =  mockMvc
					.perform(MockMvcRequestBuilders.get(USERS_ENDPOINT+"get?email=16998@gmail.com")
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isOk()).andReturn();
			String content = mvcResult.getResponse().getContentAsString();
			User f = new Gson().fromJson(content, User.class);
			assertTrue(f.getId()==createUser().getId());

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}
	@Test
	public void consultaUserNotFoundTest() {
		try {
			mockMvc
			.perform(MockMvcRequestBuilders.get(USERS_ENDPOINT+"get?email=16998@gmail.com1")
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isBadRequest()).andReturn();

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}
	
	@Test
	public void consultaUserNotFoundTest1() {
		try {
			Optional<User> userOptional = Optional.ofNullable(null);
			System.out.println(userOptional.isPresent());
			when(userService.findByEmail(ArgumentMatchers.any(String.class))).thenReturn(userOptional);
			 mockMvc
					.perform(MockMvcRequestBuilders.get(USERS_ENDPOINT+"get?email=1116998@gmail.com")
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isFailedDependency()).andReturn();
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}


	@Test
	public void consultaAllUserTest() {
		try {
			List<User> facturas = new ArrayList();
			facturas.add(createUser());
			facturas.add(createUser());
			facturas.add(createUser());
			when(userService.findAll()).thenReturn(facturas);
			MvcResult mvcResult =  mockMvc
					.perform(MockMvcRequestBuilders.get(USERS_ENDPOINT+"list")
							.contentType(MediaType.APPLICATION_JSON_VALUE))
					.andDo(print())
					.andExpect(status().isOk()).andReturn();
			String content = mvcResult.getResponse().getContentAsString();
			List l = new Gson().fromJson(content, List.class);
			assertTrue(l.size()==3);

		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}


}
