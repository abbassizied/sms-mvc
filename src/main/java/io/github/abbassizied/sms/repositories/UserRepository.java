package io.github.abbassizied.sms.repositories;

import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;
 
import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);

	List<User> findAllByRoles(Role role);
    
}
