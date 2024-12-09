package io.github.abbassizied.sms.forms;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class UserProfileForm {
	
	private Long id;
	
	private String email;
	
    @NotBlank(message = "First name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "The first name must contain only letters")
    private String firstName;

    @NotBlank(message = "Last name cannot be blank")
    @Pattern(regexp = "^[A-Za-z]+$", message = "The last name must contain only letters")
    private String lastName;
    
    // Logo URL: Optional     
    private MultipartFile photoUrl;   
    
    // Default Constructor 
    public UserProfileForm() {
		super();
	}    
    
    // Getters and Setters for form data
    public Long getId() { return id; }
	public void setId(Long id) { this.id = id; }    
    
    public String getEmail() { return email; } 
    public void setEmail(String email) { this.email = email; }	
	
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }    
    
    public MultipartFile getPhotoUrl() { return photoUrl; }  
    public void setPhotoUrl(MultipartFile photoUrl) { this.photoUrl = photoUrl; }    
}
