package cl.com.ionix.testbackend.persistence.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import cl.com.ionix.testbackend.persistence.model.User;
import cl.com.ionix.testbackend.persistence.repository.UserRepository;
import cl.com.ionix.testbackend.persistence.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * The Class UserServiceImpl.
 */
@Service
@Transactional
public class UserServiceImpl implements IUserService {
    
    /** The user repository. */
    @Autowired 
    private UserRepository userRepository;
    
	/**
	 * Find by email.
	 *  Método encargado de buscar un usuario dado su email
	 *
	 * @param email the email
	 * @return the optional
	 */
	@Override
	public Optional<User>  findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	/**
	 * Save.
	 *
	 * Método encargado de crear nuevos usuarios.
	 * @param u the u
	 * @return the user
	 */
	@Override
	public User save(User u) {
		return userRepository.save(u);
	}

	/**
	 * Find all.
	 *  Método encargado de buscar todos los usuarios.
	 *
	 * @return the list
	 */
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}
}