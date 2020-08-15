package cl.com.ionix.testbackend.persistence.service.impl;

import java.time.Duration;
import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import cl.com.ionix.testbackend.persistence.service.ISearchService;
import cl.com.ionix.testbackend.util.IEncryptUtil;
import cl.com.ionix.testbackend.web.dto.ResultDto;
import cl.com.ionix.testbackend.web.dto.SearchResponseDto;

/**
 * The Class ISearchServiceImpl.
 */
@Service
public class SearchServiceImpl implements ISearchService{
	   
	/** The encrypt util. */
	@Autowired
	private IEncryptUtil encryptUtil;
	
    @Autowired
    private RestTemplate restTemplate;
	
	/** The search url. */
	@Value("${cipher.url}")
	private String searchUrl;


	/**
	 * Search.
	 * Método encargado de la búsquda desde Sandbox
	 *
	 * @param rut the rut
	 * @return the search response dto
	 */
	@Override
	public SearchResponseDto search(String rut) {
		SearchResponseDto responseDto  = null;
        Instant begin = Instant.now();
		ResponseEntity<String> response = restTemplate
				  .getForEntity((searchUrl+encryptUtil.encrypt(rut)), String.class);
        Instant end = Instant.now();
		if(response.getStatusCode().equals(HttpStatus.OK)) {
		    long size = extractResult(response.getBody());
		    ResultDto resultDto = new ResultDto.Builder().registerCount(size).build();
		    responseDto = createSearchResponseDto(response,resultDto,begin,end);
		} else {
			responseDto = createSearchResponseDto(response,null,begin,end);
		}
		return responseDto;
	}


	/**
	 * Extract result.
	 * 
	 * Extrae el número de items que se encunetran en el body
	 *
	 * @param body the body
	 * @return the long
	 */
	private long extractResult(String body) {
		JsonElement jelement = JsonParser.parseString(body);
	    JsonObject  jobject = jelement.getAsJsonObject();
	    JsonObject  result = jobject.getAsJsonObject("result");
	    JsonArray array = result.getAsJsonArray("items");
	    return array.size();
	}


	/**
	 * Creates the search response dto.
	 * 
	 * Crea el la respuesta de salid del servicio.
	 *
	 * @param response the response
	 * @param resultDto the result dto
	 * @param begin the begin
	 * @param end the end
	 * @return the search response dto
	 */
	private SearchResponseDto createSearchResponseDto(ResponseEntity<String> response, ResultDto resultDto,
			Instant begin, Instant end) {
		return new SearchResponseDto.Builder()
	    		.result(resultDto)
	    		.description(response.getStatusCode().name())
	    		.elapsedTime(Duration.between(begin, end).toMillis())
	    		.responseCode(response.getStatusCode().value())
	    		.build();
	}
}