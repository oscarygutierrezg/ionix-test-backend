package cl.com.ionix.testbackend.persistence.service.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import cl.com.ionix.testbackend.persistence.service.ISearchService;
import cl.com.ionix.testbackend.util.IEncryptUtil;
import cl.com.ionix.testbackend.web.dto.SearchResponseDto;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import cl.com.ionix.testbackend.TestBackendApplication;


@SpringBootTest
@ContextConfiguration(classes = TestBackendApplication.class)
class SearchServiceImplTest {
	
	private static final String ENCRYPT_RUT = "FyaSTkGi8So=";
	private static final String RUT = "1-9";

	@Autowired
	ISearchService searchService;
	
	@MockBean
	IEncryptUtil encryptUtil;
	
	@MockBean
    RestTemplate restTemplate;
	

	@Value("${cipher.url}")
	private String searchUrl;

	@Test
    public void searchOKTest() {
        String resp = "{\"responseCode\":0,\"description\":\"OK\",\"result\":{\"items\":[{\"name\":\"John\",\"detail\":{\"email\":\"jdoe@gmail.com\",\"phone_number\":\"+130256897875\"}},{\"name\":\"Anna\",\"detail\":{\"email\":\"asmith@gmail.com\",\"phone_number\":\"+5689874521\"}},{\"name\":\"Peter\",\"detail\":{\"email\":\"pjones@gmail.com\",\"phone_number\":\"+668978542365\"}}]}}";
        when(encryptUtil.encrypt(ArgumentMatchers.any(String.class))).thenReturn(ENCRYPT_RUT);
        when(restTemplate.getForEntity(searchUrl+ENCRYPT_RUT, String.class))
          .thenReturn(new ResponseEntity<String>(resp, HttpStatus.OK));
        SearchResponseDto searchResponseDto = searchService.search(RUT);
        assertTrue(searchResponseDto.getResponseCode()==HttpStatus.OK.value() );
        assertTrue(searchResponseDto.getDescription().equals(HttpStatus.OK.name()) );
    }
	@Test
	public void searchNotOKTest() {
		String resp = "{\"responseCode\":0,\"description\":\"OK\",\"result\":{\"items\":[{\"name\":\"John\",\"detail\":{\"email\":\"jdoe@gmail.com\",\"phone_number\":\"+130256897875\"}},{\"name\":\"Anna\",\"detail\":{\"email\":\"asmith@gmail.com\",\"phone_number\":\"+5689874521\"}},{\"name\":\"Peter\",\"detail\":{\"email\":\"pjones@gmail.com\",\"phone_number\":\"+668978542365\"}}]}}";
		when(encryptUtil.encrypt(ArgumentMatchers.any(String.class))).thenReturn(ENCRYPT_RUT);
		when(restTemplate.getForEntity(searchUrl+ENCRYPT_RUT, String.class))
		.thenReturn(new ResponseEntity<String>(resp, HttpStatus.INTERNAL_SERVER_ERROR));
		SearchResponseDto searchResponseDto = searchService.search(RUT);
		assertTrue(searchResponseDto.getResponseCode()==HttpStatus.INTERNAL_SERVER_ERROR.value() );
		assertTrue(searchResponseDto.getDescription().equals(HttpStatus.INTERNAL_SERVER_ERROR.name()) );
	}

}
