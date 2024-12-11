package io.github.abbassizied.sms.entities;

import io.github.abbassizied.sms.enums.EPrivilege;

import jakarta.persistence.*;

@Entity
@Table(name = "privileges")
public class Privilege extends BaseEntity {
 
	@Enumerated(EnumType.STRING)
	@Column(unique = true, nullable = false, length = 45)
	private EPrivilege name;

    // Constructors
	public Privilege() {
		super();
	}

	public Privilege(EPrivilege name) {
		super();
		this.name = name;
	} 

	public EPrivilege getName() {
		return name;
	}

	public void setName(EPrivilege name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Privilege [name=" + name + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	} 
}
