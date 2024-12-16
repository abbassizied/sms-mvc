package io.github.abbassizied.sms.entities;

import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "purchases")
public class Purchase extends BaseEntity {  

    @Column(nullable = false)
    private Double totalAmount = 0.0;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PurchaseItem> purchaseItems = new ArrayList<>(); // List of purchase items

    // Default constructor
	public Purchase() {
		super();
	}

    // Getters and setters
	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public List<PurchaseItem> getPurchaseItems() {
		return purchaseItems;
	}

	public void setPurchaseItems(List<PurchaseItem> purchaseItems) {
		this.purchaseItems = purchaseItems;
	}

	@Override
	public String toString() {
		return "Purchase [totalAmount=" + totalAmount + ", supplier=" + supplier + ", purchaseItems=" + purchaseItems
				+ ", id=" + id + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
	}	
}
