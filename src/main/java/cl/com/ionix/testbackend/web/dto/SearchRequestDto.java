package cl.com.ionix.testbackend.web.dto;

import cl.com.ionix.testbackend.validator.ValidRut;

/**
 * The Class SearchRequestDto.
 */
public class SearchRequestDto {

	/** The rut. */
	@ValidRut
	private String rut;

	/**
	 * Gets the rut.
	 *
	 * @return the rut
	 */
	public String getRut() {
		return rut;
	}

	/**
	 * Sets the rut.
	 *
	 * @param rut the new rut
	 */
	public void setRut(String rut) {
		this.rut = rut;
	}


}
