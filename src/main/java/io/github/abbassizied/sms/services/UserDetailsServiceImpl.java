package io.github.abbassizied.sms.services;

// import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
 
import io.github.abbassizied.sms.entities.User;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Find the user by email
		User user = userService.findByEmail(username);
		// Return a Spring Security User object
		return new io.github.abbassizied.sms.services.SecurityUser(user);
	}

}

/*
 * with email, password, and roles (authorities) return new
 * org.springframework.security.core.userdetails.User( user.getEmail(), //
 * Username (email) user.getPassword(), // Password user.getRoles() // Convert
 * Set<Role> to Set<GrantedAuthority> .stream() .map(role -> new
 * SimpleGrantedAuthority(role.getName())) // Map role name to
 * SimpleGrantedAuthority .collect(Collectors.toSet()) );
 */
