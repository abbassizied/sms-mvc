package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;
import java.util.List;

import io.github.abbassizied.sms.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity { 

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
		return "Order [orderStatus=" + orderStatus + ", totalAmount=" + totalAmount + ", customer=" + customer
				+ ", orderItems=" + orderItems + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt
				+ "]";
	}
}
