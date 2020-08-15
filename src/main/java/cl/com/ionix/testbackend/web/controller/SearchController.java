package cl.com.ionix.testbackend.web.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.com.ionix.testbackend.persistence.service.ISearchService;
import cl.com.ionix.testbackend.web.dto.SearchRequestDto;
import cl.com.ionix.testbackend.web.dto.SearchResponseDto;

/**
 * The Class SearchController.
 * 
 * API de busqueda cifrada
 */
@RestController
@RequestMapping("/v1/search")
public class SearchController {

	/** The search service. */
	@Autowired
	private ISearchService searchService;


	/**
	 * Search.
	 * 
	 * Método encargado de la búsquda desde Sandbox
	 * 
	 *
	 * @param u the u
	 * @return the response entity
	 */
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SearchResponseDto>  search(@Valid @RequestBody SearchRequestDto u) {
		return ResponseEntity.status(HttpStatus.OK).body(searchService.search(u.getRut()));
	}
}
