package cl.com.ionix.testbackend.web.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.Gson;

import cl.com.ionix.testbackend.TestBackendApplication;
import cl.com.ionix.testbackend.persistence.service.ISearchService;
import cl.com.ionix.testbackend.web.dto.ResultDto;
import cl.com.ionix.testbackend.web.dto.SearchResponseDto;

@SpringBootTest
@ContextConfiguration(classes = TestBackendApplication.class)
class SearchControllerTest {


	private String SEARCH_ENDPOINT = "/v1/search";
	private String JSON_BODY = "{\"rut\":\"60910000-1\"}";
	private String JSON_BODY_BAB_REQUEST = "{\"rut\":\"1-0\"}";


	private MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;


	@MockBean
	private ISearchService searchService;
	

	@MockBean
    RestTemplate restTemplate;


	@BeforeEach
	protected void setUp() {
		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	

	@Test
	public void search() throws Exception {
		when(searchService.search(ArgumentMatchers.any(String.class))).thenReturn(createSearchResponse());
		MvcResult mvcResult =  mvc
				.perform(MockMvcRequestBuilders.post(SEARCH_ENDPOINT)
						.content(JSON_BODY)
						.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print())
				.andExpect(status().isOk()).andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		SearchResponseDto searchResponseDto = new Gson().fromJson(content, SearchResponseDto.class);
		assertEquals(searchResponseDto.getDescription(),createSearchResponse().getDescription());
	}
	
	@Test
	public void searchBadRequestTest() {
		try {
			mvc
			.perform(MockMvcRequestBuilders.post(SEARCH_ENDPOINT)
					.content(JSON_BODY_BAB_REQUEST)
					.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andDo(print())
			.andExpect(status().isBadRequest()).andReturn();
		
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("Exception: " + e);
		}
	}

	public SearchResponseDto createSearchResponse() {
		ResultDto resultDto = new ResultDto.Builder().registerCount(3).build();
		return new SearchResponseDto.Builder()
				.result(resultDto)
				.description(HttpStatus.OK.name())
				.elapsedTime(0)
				.responseCode(HttpStatus.OK.value())
				.build();
	}


}
