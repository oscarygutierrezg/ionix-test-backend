package cl.com.ionix.testbackend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import javax.validation.ConstraintViolationException;

/**
 * The Class CustomizedResponseEntityExceptionHandler.
 * 
 *  Esta clase filtra todos las  excepciones para crear una respuesta manejada.
 * 
 * Source https://www.springboottutorial.com/spring-boot-exception-handling-for-rest-services
 */
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {


	@ExceptionHandler(Exception.class)
	public final ResponseEntity<String> handleAllExceptions(Exception ex, WebRequest request) {
		request.getDescription(false);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
	}
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<String> handleInvalidParameterException(ConstraintViolationException ex, WebRequest request) {
		request.getDescription(false);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
	}
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		request.getDescription(false);
		return ResponseEntity.status(HttpStatus.FAILED_DEPENDENCY).body(ex.getMessage()+" User Not Found");
	}
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public final ResponseEntity<String> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest request) {
		request.getDescription(false);
		return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body("Email Already Exists");
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		StringBuilder sb = new StringBuilder("Validation Failed ");
		for(ObjectError error :ex.getBindingResult().getAllErrors()) {
			FieldError fieldError = (FieldError) error;	
			sb.append(fieldError.getField()+" "+error.getDefaultMessage()+" ");
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(sb.toString());
	} 
}
