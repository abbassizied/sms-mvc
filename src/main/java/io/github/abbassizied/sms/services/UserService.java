package io.github.abbassizied.sms.services;

import io.github.abbassizied.sms.entities.User;
import io.github.abbassizied.sms.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder; // Use PasswordEncoder

	// Constructor injection for dependencies
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	public User createUser(User user) {
		if (user.getRoles().isEmpty()) {
			throw new IllegalArgumentException("At least one role must be provided");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		if (user.getRoles().isEmpty()) {
			throw new IllegalArgumentException("At least one role must be provided");
		}

        // Retrieve the existing user from the database
        User existingUser = getUserById(user.getId());

        // Update mutable fields
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setActive(user.getActive());
        existingUser.setRoles(user.getRoles());

        // Retain the original email and password
        // These fields will NOT be updated even if they are sent in the update request

        // Save the updated user back to the database
        return userRepository.save(existingUser); 
	}	
	

	public User getUserById(Long id) {
		return userRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
	}

	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}

	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public boolean isEmailUnique(String email) {
		// Logic to check if the email is unique (e.g., querying the database)
		return userRepository.findByEmail(email).isEmpty();
		// OR return !userRepository.findByEmail(email).isPresent();
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email)
				// If user not found, throw an exception
				.orElseThrow(() -> new EntityNotFoundException("User with email: " + email + " not found!"));
	}


	public void updatePassword(User user) {
		// Validate the user exists, and fetch the user if needed
		User existingUser = getUserById(user.getId());
		// Encode and Update the new password 
		existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
		// Call the repository method to update the password
		userRepository.save(existingUser);
	}

}
