package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.util.List;

import io.github.abbassizied.sms.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@Column(nullable = false)
	private String firstName;

	@Column(nullable = false)
	private String lastName;

	private String email;

	private Integer phone;

	@Column(columnDefinition = "TEXT")
	private String adress;

	@Enumerated(EnumType.STRING) // Use EnumType.STRING to store enum names in the database
	@Column(name = "order_status", nullable = false)
	private OrderStatus orderStatus;

	@Column(unique = true, nullable = false)
	private Double totalAmount;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems;

	// Default constructor
	public Order() {
		super();
	}

	// Getters and setters
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	// Getter and Setter for orderStatus
	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", phone=" + phone
				+ ", adress=" + adress + ", orderStatus=" + orderStatus + ", totalAmount=" + totalAmount + ", customer="
				+ customer + ", orderItems=" + orderItems + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
}
