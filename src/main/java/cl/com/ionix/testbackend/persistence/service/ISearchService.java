package cl.com.ionix.testbackend.persistence.service;

import cl.com.ionix.testbackend.web.dto.SearchResponseDto;


/**
 * The Interface ISearchService.
 */
public interface ISearchService {

	/**
	 * Search.
	 *
	 * @param rut the rut
	 * @return the search response dto
	 */
	public SearchResponseDto search(String rut);
}