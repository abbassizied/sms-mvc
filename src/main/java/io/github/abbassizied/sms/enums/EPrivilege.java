package io.github.abbassizied.sms.enums;

import org.springframework.security.core.GrantedAuthority;

// Resource-Action Pattern
public enum EPrivilege implements GrantedAuthority {
	// User privileges
	USER_READ("USER_READ"), 
	USER_WRITE("USER_WRITE"),  
	USER_UPDATE("USER_UPDATE"), 
	USER_DELETE("USER_DELETE"),
	// Role privileges
	ROLE_READ("ROLE_READ"), 
	ROLE_WRITE("ROLE_WRITE"),  
	ROLE_UPDATE("ROLE_UPDATE"), 
	ROLE_DELETE("ROLE_DELETE");
	// Add other privileges as needed
	private final String name;

	EPrivilege(String name) {
		this.name = name;
	}

	@Override
	public String getAuthority() {
		return this.name;
	}

}
