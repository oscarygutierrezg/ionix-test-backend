package cl.com.ionix.testbackend.web.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import cl.com.ionix.testbackend.persistence.model.User;
import cl.com.ionix.testbackend.validator.ValidEmail;
/**
 * The Class UserDto.
 */
public class UserDto {
	
	/** The name. */
	@NotNull
	@NotEmpty
	private String name;
	
	/** The username. */
	@NotNull
	@NotEmpty
	private String username;
	
	/** The email. */
	@ValidEmail
	private String email;
	
	/** The phone. */
	@NotNull
	@NotEmpty
	private String phone;
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	
	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the phone.
	 *
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	
	/**
	 * Sets the phone.
	 *
	 * @param phone the new phone
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}	

	
	/**
	 * The Class Builder.
	 */
	public static class  Builder{

		/** The dto. */
		private UserDto dto;

		/**
		 * Instantiates a new builder.
		 *
		 * @param dto the dto
		 */
		public Builder(UserDto dto) {
			this.dto=dto;
		}

		
		/**
		 * Builds the.
		 *
		 * @return the user
		 */
		public User build() {
			User u = new User();
			u.setEmail(dto.getEmail());
			u.setName(dto.getName());
			u.setPhone(dto.getPhone());
			u.setUsername(dto.getUsername());
			return u;
		}
	}
}
