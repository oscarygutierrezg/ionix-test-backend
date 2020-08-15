package cl.com.ionix.testbackend.exception;

/**
 * The Class UserNotFoundException.
 */
public class UserNotFoundException extends RuntimeException  {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8186704783568067163L;

	/**
	 * Instantiates a new user not found exception.
	 *
	 * @param message the message
	 */
	public UserNotFoundException(String message) {
		super(message);
	}

}
