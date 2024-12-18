package io.github.abbassizied.sms.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "company_settings")
/**
 * This entity is intended to have only one record in the database.
 * Any operations on this table should maintain this single-record constraint.
 */
public class CompanySetting extends BaseEntity {

    @Column(nullable = false)
    private String companyName;

    @Column
    private String companyLogoUrl;

    @Column
    private String email;

    @Column
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    @Column(columnDefinition = "TEXT")
    private String aboutUs;

    // Default constructor
    public CompanySetting() {
        super();
    }

    // Getters and Setters
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
		return "CompanySetting [companyName=" + companyName + ", companyLogoUrl=" + companyLogoUrl + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", aboutUs=" + aboutUs + ", id=" + id + ", createdAt="
				+ createdAt + ", updatedAt=" + updatedAt + "]";
	}
}
