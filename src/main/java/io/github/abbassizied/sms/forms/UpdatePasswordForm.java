package io.github.abbassizied.sms.forms;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdatePasswordForm {

	private Long id;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
	private String password;

	// Default Constructor
	public UpdatePasswordForm() {
		super();
	}

	// Getters and Setters for form data
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
