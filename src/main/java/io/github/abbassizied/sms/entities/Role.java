package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; 

import io.github.abbassizied.sms.forms.validations.UniqueRoleName;

@Entity
@Table(name = "roles")
public class Role extends BaseEntity{

	@Id
	@Column(nullable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@UniqueRoleName // Custom validator to check for uniqueness
	@Pattern(regexp = "^ROLE_[A-Z]+$", message = "Role name must start with 'ROLE_' followed by uppercase letters only")
	@NotBlank(message = "Role name cannot be blank")
	@Column(nullable = false, unique = true)
	private String name;
  
	// Constructors, getters, and setters
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
