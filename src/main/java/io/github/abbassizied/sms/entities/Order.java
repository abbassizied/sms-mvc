package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import io.github.abbassizied.sms.enums.OrderStatus;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity { 

	@Enumerated(EnumType.STRING) // Use EnumType.STRING to store enum names in the database
	@Column(name = "order_status", nullable = false)
	private OrderStatus orderStatus = OrderStatus.IN_PROGRESS;

	@Column(unique = true, nullable = false)
	private Double totalAmount = 0.0;;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> orderItems = new ArrayList<>();

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
		System.out.println("****************" + customer);
	}

	public List<OrderItem> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		this.orderItems = orderItems;
	}

    @Override
    public String toString() {
        return "Order{" +
               "id=" + id +
               ", customer=" + (customer != null ? customer.getId() : "null") + // Avoid printing customer object fully
               ", totalAmount=" + totalAmount +
               ", orderStatus='" + orderStatus + '\'' +
               ", orderItemsCount=" + (orderItems != null ? orderItems.size() : 0) + // Avoid printing the list of order items
               '}';
    }	 
}
