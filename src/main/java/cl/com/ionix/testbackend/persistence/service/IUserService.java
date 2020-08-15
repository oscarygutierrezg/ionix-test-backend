package cl.com.ionix.testbackend.persistence.service;

import java.util.List;
import java.util.Optional;

import cl.com.ionix.testbackend.persistence.model.User;

/**
 * The Interface IUserService.
 */
public interface IUserService {

	/**
	 * Save.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User save(User user);

	/**
	 * Find by email.
	 *
	 * @param email the email
	 * @return the optional
	 */
	public Optional<User> findByEmail(String email);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<User> findAll();
}