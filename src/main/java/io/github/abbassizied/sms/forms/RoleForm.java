package io.github.abbassizied.sms.forms;

import jakarta.validation.constraints.*;
import java.util.*;

import io.github.abbassizied.sms.entities.Privilege;
import io.github.abbassizied.sms.forms.validations.UniqueRoleName;
import io.github.abbassizied.sms.forms.validations.NotEmptyPrivileges;

public class RoleForm {

	private Long id;
	
    @UniqueRoleName(groups = OnCreate.class) // Custom validator to check for uniqueness
    @Pattern(regexp = "^ROLE_[A-Z]+$", message = "Role name must start with 'ROLE_' followed by uppercase letters only", groups = {OnCreate.class, OnUpdate.class})
    @NotBlank(message = "Role name cannot be blank", groups = {OnCreate.class, OnUpdate.class})
    private String name;

    @NotEmptyPrivileges(message = "Privileges cannot be empty", groups = {OnCreate.class, OnUpdate.class}) // Custom constraint on privileges field    
    private Set<Privilege> privileges = new HashSet<>();    
    
    // Constructor
    public RoleForm() {}

    
	public RoleForm(String name) {
        this.name = name;
    }

    // Getters and setters	
    public Long getId() {
		return id;
	}
 
	public void setId(Long id) {
		this.id = id;
	}

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        this.name = name; 
    }
	public Set<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Set<Privilege> privileges) {
		this.privileges = privileges;
	}
 
	@Override
	public String toString() {
		return "RoleForm [id=" + id + ", name=" + name + ", privileges=" + privileges + "]";
	} 
}
