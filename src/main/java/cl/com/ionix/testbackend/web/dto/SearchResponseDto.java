package cl.com.ionix.testbackend.web.dto;


// TODO: Auto-generated Javadoc
/**
 * The Class SearchResponseDto.
 */
public class SearchResponseDto {

	/** The response code. */
	private int responseCode;
	
	/** The description. */
	private String description;
	
	/** The elapsed time. */
	private long elapsedTime;
	
	/** The result. */
	private ResultDto result;

	/**
	 * Instantiates a new search response dto.
	 */
	private SearchResponseDto() {

	}

	/**
	 * Gets the response code.
	 *
	 * @return the response code
	 */
	public long getResponseCode() {
		return responseCode;
	}

	/**
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Gets the elapsed time.
	 *
	 * @return the elapsed time
	 */
	public long getElapsedTime() {
		return elapsedTime;
	}

	/**
	 * Gets the result.
	 *
	 * @return the result
	 */
	public ResultDto getResult() {
		return result;
	}
	
	/**
	 * Sets the response code.
	 *
	 * @param responseCode the new response code
	 */
	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the elapsed time.
	 *
	 * @param elapsedTime the new elapsed time
	 */
	public void setElapsedTime(long elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	/**
	 * Sets the result.
	 *
	 * @param resultObject the new result
	 */
	public void setResult(ResultDto resultObject) {
		this.result = resultObject;
	}

	/**
	 * The Class Builder.
	 */
	public static class  Builder{

		/** The response code. */
		private int responseCode;
		
		/** The description. */
		private String description;
		
		/** The elapsed time. */
		private long elapsedTime;
		
		/** The result. */
		private ResultDto result;

		/**
		 * Response code.
		 *
		 * @param responseCode the response code
		 * @return the builder
		 */
		public Builder responseCode(int responseCode) {
			this.responseCode=responseCode;
			return this;
		}
		
		/**
		 * Description.
		 *
		 * @param description the description
		 * @return the builder
		 */
		public Builder description(String description) {
			this.description=description;
			return this;
		}
		
		/**
		 * Elapsed time.
		 *
		 * @param elapsedTime the elapsed time
		 * @return the builder
		 */
		public Builder elapsedTime(long elapsedTime) {
			this.elapsedTime=elapsedTime;
			return this;
		}
		
		/**
		 * Result.
		 *
		 * @param result the result
		 * @return the builder
		 */
		public Builder result(ResultDto result) {
			this.result=result;
			return this;
		}


		/**
		 * Builds the.
		 *
		 * @return the search response dto
		 */
		public SearchResponseDto build() {
			SearchResponseDto u = new SearchResponseDto();
			u.setDescription(description);
			u.setElapsedTime(elapsedTime);
			u.setResponseCode(responseCode);
			u.setResult(result);
			return u;
		}
	}

}
