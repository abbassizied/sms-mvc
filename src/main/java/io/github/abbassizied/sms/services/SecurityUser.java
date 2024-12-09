package io.github.abbassizied.sms.services;

import java.util.*;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import io.github.abbassizied.sms.entities.Privilege;
import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.entities.User;

public class SecurityUser implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final User user;

	public SecurityUser(User user) {
		this.user = user;
	}

	@Override
	public String getUsername() {
		return user.getEmail();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	// hedi yelzem t3awed te5demha fi userdetailsService implem 5ater loqique kol rahou 8adi : heda juste un modeld
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<>();
		List<Privilege> privileges = new ArrayList<>();

		Collection<Role> roles = user.getRoles();

		for (Role role : roles) {

			privileges.addAll(role.getPrivileges());
		}

		for (Privilege privilege : privileges) {
			authorities.add(privilege.getName());
		}
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		// You can add real logic here, for now returning true
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// Add real logic, for now returning true
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// Add real logic, for now returning true
		return true;
	}

	@Override
	public boolean isEnabled() {
		// Use user's active status or another field to check if enabled
		return user.getActive();
	}
	
	public Long getId() {
		return user.getId();
	}	

	public String getFirstName() {
		return user.getFirstName();
	}

	public String getLastName() {
		return user.getLastName();
	}
	
	public String getPhotoUrl() {
		return user.getPhotoUrl();
	}
}
