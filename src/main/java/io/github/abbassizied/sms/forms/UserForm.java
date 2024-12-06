package io.github.abbassizied.sms.forms; 

import jakarta.validation.constraints.*;
import io.github.abbassizied.sms.forms.validations.UniqueEmail;

public class UserForm {
	private Integer id;
	
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "The first name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "The last name must contain only letters")
    private String lastName;

    @UniqueEmail
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    @Pattern(regexp = ".+@.+\\..+", message = "Please enter a valid email address (e.g., example@domain.com)")
    private String email;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    private String password;

    private Boolean active;

    // Getters and Setters for form data
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }    
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
