package io.github.abbassizied.sms.entities;

import jakarta.persistence.*; 
import java.util.*; 

@Entity
@Table(name = "roles")
public class Role extends BaseEntity { 
 
	@Column(nullable = false, unique = true)
	private String name;

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "roles_privileges",
	    joinColumns = @JoinColumn(name = "role_id"), 
	    inverseJoinColumns = @JoinColumn(name = "privilege_id"))
	private Set<Privilege> privileges = new HashSet<>();
 
	// Constructors, getters, and setters
	public Role() {
		super();
	}

	public Role(String name) {
		super();
		this.name = name;
	}
 
	public String getName() {
		return name;
	}

	public void setName(final String name) {
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
		return "Role [id=" + id + ", name=" + name + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}

}
