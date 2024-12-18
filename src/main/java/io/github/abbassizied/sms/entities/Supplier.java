package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "suppliers")
public class Supplier extends BaseEntity { 

    @Column(nullable = false)
    private String name;

    @Column
    private String logoUrl;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private String address;

    @OneToMany(mappedBy = "supplier")
    private List<Product> products;

    // Default constructor
	public Supplier() {
		super();
	}

    // Getters and setters	  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

	public String getLogoUrl() {
		return logoUrl;
	}

	public void setLogoUrl(String logoUrl) {
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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "Supplier [id=" + id + ", companyName=" + name + ", logoUrl=" + logoUrl + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + "]";
	}
 	
}
