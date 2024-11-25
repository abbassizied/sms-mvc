package io.github.abbassizied.sms.forms; 
/*
 * https://spring.io/guides/gs/validating-form-input
 * https://reflectoring.io/bean-validation-with-spring-boot/
 * 
 */

import jakarta.validation.constraints.*;
import org.springframework.web.multipart.MultipartFile;
import io.github.abbassizied.sms.forms.validations.ValidMultipartFile;

public class SupplierForm {
	
	private Long id;
	
    // Company Name: Required, not empty, between 2 and 30 characters
    @NotBlank(message = "{supplier.companyName.notBlank}", groups = { OnCreate.class, OnUpdate.class })
    @Size(min = 2, max = 30, message = "{supplier.companyName.size}", groups = { OnCreate.class, OnUpdate.class })
    private String name; 

    // Logo URL: Optional, but if provided must be a valid URL 
    @ValidMultipartFile(groups = OnCreate.class)
    private MultipartFile logoUrl;

    // Email: Required, must be a valid email format
    @NotBlank(message = "{supplier.email.notBlank}", groups = { OnCreate.class, OnUpdate.class })
    @Email(message = "{supplier.email.invalid}", groups = { OnCreate.class, OnUpdate.class })
    private String email;

    // Phone: Required, must match the specified pattern
    @Pattern(regexp = "^\\+216[\\s-]?\\d{8}$|^216[\\s-]?\\d{8}$|^\\+?[0-9]{8}$", message = "{supplier.phone.invalid}", groups = { OnCreate.class, OnUpdate.class })
    private String phone;

    // Address: Required, must not exceed 100 characters
    @Size(max = 100, message = "{supplier.address.size}", groups = { OnCreate.class, OnUpdate.class })
    private String address;

    // Constructors

    /**
     * Default constructor for form binding.
     */
    public SupplierForm() {
    } 

    // Getters and Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
    public String getName() {
        return name;
    }

    public void setName(String companyName) {
        this.name = companyName;
    }

    public MultipartFile getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(MultipartFile logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPhone() {
        return phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    // Optional: toString method for debugging purposes

    @Override
    public String toString() {
        return "SupplierForm{" +
                "companyName='" + name + '\'' +
                ", logoUrl='" + logoUrl + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}