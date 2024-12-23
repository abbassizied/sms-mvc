package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.util.List; 

@Entity
@Table(name = "customers")
public class Customer extends Person {

    @Column
    private String phone;

    @Column(columnDefinition = "TEXT")
    private String address;

    /**** One To Many ****/
    @OneToMany(mappedBy = "customer")
    private List<Order> orders;
    
    // Default constructor 
	public Customer() {
		super();
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

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", orders=" + orders + "]";
	} 
}
