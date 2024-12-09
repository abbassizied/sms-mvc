package io.github.abbassizied.sms.repositories; 

import io.github.abbassizied.sms.entities.Role;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	boolean existsByNameIgnoreCase(String name);
	Optional<Role> findByName(String name); 
}
