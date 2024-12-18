package io.github.abbassizied.sms.forms;

import jakarta.validation.constraints.*;

public class CompanySettingForm {

    private Long id;

    @NotBlank(message = "Company name is required")
    private String companyName;

    private String companyLogoUrl;

    @Email(message = "Please provide a valid email address")
    private String email;

    // Phone: Required, must match Tunisian phone number format
    @NotBlank(message = "Phone number is required")
    @Pattern(
        regexp = "^\\+216[\\s-]?\\d{8}$|^216[\\s-]?\\d{8}$|^\\d{8}$",
        message = "Please provide a valid Tunisian phone number (e.g., +21612345678 or 12345678)"
    )
    private String phone;

    private String address;

    private String aboutUs;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogoUrl() {
        return companyLogoUrl;
    }

    public void setCompanyLogoUrl(String companyLogoUrl) {
        this.companyLogoUrl = companyLogoUrl;
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

    public String getAboutUs() {
        return aboutUs;
    }

    public void setAboutUs(String aboutUs) {
        this.aboutUs = aboutUs;
    }

	@Override
	public String toString() {
		return "CompanySettingForm [id=" + id + ", companyName=" + companyName + ", companyLogoUrl=" + companyLogoUrl
				+ ", email=" + email + ", phone=" + phone + ", address=" + address + ", aboutUs=" + aboutUs + "]";
	}  
}
