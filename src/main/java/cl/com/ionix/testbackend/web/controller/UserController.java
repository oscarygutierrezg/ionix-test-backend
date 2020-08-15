package cl.com.ionix.testbackend.web.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cl.com.ionix.testbackend.exception.EmailAlreadyExistsException;
import cl.com.ionix.testbackend.exception.UserNotFoundException;
import cl.com.ionix.testbackend.persistence.model.User;
import cl.com.ionix.testbackend.persistence.service.IUserService;
import cl.com.ionix.testbackend.validator.ValidEmail;
import cl.com.ionix.testbackend.web.dto.UserDto;

/**
 * The Class UserController.
 * 
 * API de usuarios
 */
@RestController
@RequestMapping("/v1/users")
@Validated
public class UserController {

	/** The user service. */
	@Autowired
	private IUserService userService;


	/**
	 * Creates the user.
	 * 
	 * Método encargado de crear nuevos usuarios.
	 *
	 * @param u the u
	 * @return the response entity
	 */
	@PostMapping(value = "/add",produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User>  createUser(@Valid @RequestBody UserDto u) {
		Optional<User> uOptional = userService.findByEmail(u.getEmail());
		if (uOptional.isPresent())
			throw new EmailAlreadyExistsException();
		User user = userService.save(new UserDto.Builder(u).build()) ;
		return ResponseEntity.status(HttpStatus.CREATED).body(user);
	}

	/**
	 * Retrieve user.
	 * 
	 * Método encargado de buscar un usuario dado su email
	 *
	 * @param email the email
	 * @return the response entity
	 */
	@GetMapping(value = "/get",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User>  retrieveUser(@RequestParam @ValidEmail String email) {
		Optional<User> user = userService.findByEmail(email);
		if (!user.isPresent())
			throw new UserNotFoundException("email-" + email);
		return ResponseEntity.status(HttpStatus.OK).body(user.get());
	}

	/**
	 * Retrieve all users.
	 * 
	 * Método encargado de buscar todos los usuarios.
	 *
	 * @return the response entity
	 */
	@GetMapping(value = "/list",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object>  retrieveAllUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}

}
