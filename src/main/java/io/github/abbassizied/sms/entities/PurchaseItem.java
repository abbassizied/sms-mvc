package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "purchase_items")
public class PurchaseItem extends BaseEntity { 
	
    @Column(nullable = false)
    private Integer quantity = 0;
    
    private Double subTotalPrice = 0.0;

    @ManyToOne
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Default constructor    
	public PurchaseItem() {
		super();
	}

    // Getters and setters
	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Double getSubTotalPrice() {
		return subTotalPrice;
	}

	public void setSubTotalPrice(Double totalPrice) {
		this.subTotalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "PurchaseItem [quantity=" + quantity + ", subTotalPrice=" + subTotalPrice + ", purchase=" + purchase
				+ ", product=" + product + ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}	
}
