package cl.com.ionix.testbackend.web.dto;

/**
 * The Class ResultDto.
 */
public class ResultDto {

	/** The register count. */
	private long registerCount;
	
	/**
	 * Instantiates a new result dto.
	 */
	private ResultDto() {
		
	}


	/**
	 * Gets the register count.
	 *
	 * @return the register count
	 */
	public long getRegisterCount() {
		return registerCount;
	}
	
	/**
	 * Sets the register count.
	 *
	 * @param registerCount the new register count
	 */
	public void setRegisterCount(long registerCount) {
		this.registerCount = registerCount;
	}

	/**
	 * The Class Builder.
	 */
	public static class  Builder{

		/** The register count. */
		private long registerCount;

		/**
		 * Register count.
		 *
		 * @param registerCount the register count
		 * @return the builder
		 */
		public Builder registerCount(long registerCount) {
			this.registerCount=registerCount;
			return this;
		}

		/**
		 * Builds the.
		 *
		 * @return the result dto
		 */
		public ResultDto build() {
			ResultDto u = new ResultDto();
			u.setRegisterCount(registerCount);
			return u;
		}
	}


}
