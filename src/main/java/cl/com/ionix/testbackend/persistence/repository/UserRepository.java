package cl.com.ionix.testbackend.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cl.com.ionix.testbackend.persistence.model.User;

/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find by email.
     *
     * @param email the email
     * @return the optional
     */
    public Optional<User> findByEmail(String email);
    
    
}