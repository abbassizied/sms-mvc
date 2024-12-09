package io.github.abbassizied.sms.forms; 

import jakarta.validation.constraints.*;

import java.util.HashSet;
import java.util.Set;

import io.github.abbassizied.sms.entities.Role;
import io.github.abbassizied.sms.forms.validations.UniqueEmail;

import org.springframework.web.multipart.MultipartFile; 

public class UserForm {
	
	private Long id;
	
    @NotBlank(message = "First name cannot be blank", groups = { OnCreate.class, OnUpdate.class })
    @Pattern(regexp = "^[A-Za-z]+$", message = "The first name must contain only letters", groups = { OnCreate.class, OnUpdate.class })
    private String firstName;

    @NotBlank(message = "Last name cannot be blank", groups = { OnCreate.class, OnUpdate.class })
    @Pattern(regexp = "^[A-Za-z]+$", message = "The last name must contain only letters", groups = { OnCreate.class, OnUpdate.class })
    private String lastName;

    @UniqueEmail
    @NotBlank(message = "Email cannot be blank", groups = OnCreate.class)
    @Email(message = "Email should be valid", groups = OnCreate.class)
    @Pattern(regexp = ".+@.+\\..+", message = "Please enter a valid email address (e.g., example@domain.com)", groups = OnCreate.class)
    private String email;

    @NotBlank(message = "Password cannot be blank", groups = OnCreate.class)
    @Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters", groups = OnCreate.class)
    private String password;

    // Logo URL: Optional     
    private MultipartFile photoUrl;  
    
    private Boolean active;

    @NotEmpty(message = "At least one role must be selected", groups = { OnCreate.class, OnUpdate.class })    
    private Set<Role> roles = new HashSet<>();
    
    // Default Constructor 
    public UserForm() {
		super();
	}    
    
    // Getters and Setters for form data
    public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }    
    
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public MultipartFile getPhotoUrl() { return photoUrl; } 
	public void setPhotoUrl(MultipartFile photoUrl) { this.photoUrl = photoUrl; }    
    
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
    
    public Set<Role> getRoles() { return roles; }
    public void setRoles(Set<Role> roles) { this.roles = roles; }

	@Override
	public String toString() {
		return "UserForm [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", photoUrl=" + photoUrl + ", active=" + active + ", roles=" + roles + "]";
	} 
}
